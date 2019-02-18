package com.michaelbarlow.fairfaxapplication.newsarticles.ui

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.michaelbarlow.fairfaxapplication.newsarticles.model.NewsArticle
import com.michaelbarlow.fairfaxapplication.newsarticles.viewmodel.NewsArticleViewModel
import com.michaelbarlow.fairfaxapplication.R
import com.michaelbarlow.fairfaxapplication.Resource
import kotlinx.android.synthetic.main.activity_news_articles.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * The main Activity class, shows list of news articles.
 */
class NewsArticlesActivity : AppCompatActivity() {

    private val newsArticleViewModel: NewsArticleViewModel by viewModel()

    private lateinit var newsArticleListAdapter: NewsArticleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        newsArticleRecyclerView.layoutManager = LinearLayoutManager(this)

        newsArticleListAdapter = NewsArticleListAdapter()
        newsArticleRecyclerView.adapter = newsArticleListAdapter

        // Sets UI based on network status and data
        newsArticleViewModel.getNewsArticles().observe(this, Observer<Resource<List<NewsArticle>>>{ resource ->
            when(resource?.status){
                Resource.Status.ERROR -> {
                    setErrorStatus()
                }
                Resource.Status.LOADING -> {
                    setLoadingStatus()
                }
                Resource.Status.SUCCESS -> {
                    if (resource.data.isNullOrEmpty()){
                        statusText.visibility = View.VISIBLE
                        statusText.text = getString(R.string.no_articles_message)
                    } else {
                        statusText.visibility = View.GONE
                    }
                    newsArticleListAdapter.newsArticleList = resource.data!!
                }
            }
        })
        setLoadingStatus()
    }

    private fun setLoadingStatus() {
        statusText.text = getString(R.string.status_text_loading)
        statusText.setTextColor(Color.BLACK)
        statusText.visibility = View.VISIBLE
    }

    private fun setErrorStatus() {
        statusText.text = getString(R.string.status_text_error)
        statusText.setTextColor(Color.RED)
        statusText.visibility = View.VISIBLE
    }
}
