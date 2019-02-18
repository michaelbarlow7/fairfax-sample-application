package com.michaelbarlow.fairfaxapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class NewsArticleViewModel(val newsArticleRepository: NewsArticleRepository) : ViewModel() {

    private val newsArticles: MutableLiveData<List<NewsArticle>> by lazy {
        MutableLiveData<List<NewsArticle>>().also {
            loadData()
        }
    }

    private fun loadData() {
        // load from newsArticleRepository and set in
    }

    fun getNewsArticles(): LiveData<List<NewsArticle>> {
        return newsArticles
    }
}