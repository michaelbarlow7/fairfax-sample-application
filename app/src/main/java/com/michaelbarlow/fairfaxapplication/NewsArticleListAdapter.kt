package com.michaelbarlow.fairfaxapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class NewsArticleListAdapter : RecyclerView.Adapter<NewsArticleListAdapter.NewsArticleViewHolder>(){

    class NewsArticleViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false) as TextView
        return NewsArticleViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return 99
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.textView.text = "Article: $position"
    }
}