package com.michaelbarlow.fairfaxapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_article.view.*

class NewsArticleListAdapter : RecyclerView.Adapter<NewsArticleListAdapter.NewsArticleViewHolder>() {

    var newsArticleList = emptyList<NewsArticle>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class NewsArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
        return NewsArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsArticleList.size
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        // TODO Set onclick listener for view?
        val article = newsArticleList[position]
        holder.view.newsArticleHeadLine.text = article.headline
        holder.view.newsArticleAbstract.text = article.theAbstract
        holder.view.newsArticleByLine.text = article.byLine

        // Hide imageView if there's no image for the article
        val smallestImageURL = article.getSmallestImageURL()
        if (smallestImageURL == null) {
            holder.view.newsArticleImage.visibility = View.GONE
        } else {
            holder.view.newsArticleImage.visibility = View.VISIBLE
            Picasso.get()
                .load(smallestImageURL)
                .into(holder.view.newsArticleImage)
        }
    }
}