package com.michaelbarlow.fairfaxapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news_articles.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsArticlesActivity : AppCompatActivity() {

    val newsArticleViewModel: NewsArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        newsArticleRecyclerView.layoutManager = LinearLayoutManager(this)

        //TODO: Inject a viewmodel and listen for livedata updates
        // Make an adapter here
        newsArticleRecyclerView.adapter = NewsArticleListAdapter()
    }
}
