package com.michaelbarlow.fairfaxapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_news_article.view.*

class NewsArticleListAdapter : RecyclerView.Adapter<NewsArticleListAdapter.NewsArticleViewHolder>(){

    class NewsArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsArticleHeadline = view.newsArticleHeadLine
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
        return NewsArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 99
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
//        holder.newsArticleHeadline.text = "Article: $position"
    }
}