package com.michaelbarlow.fairfaxapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsArticleViewModel(val newsArticleRepository: NewsArticleRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
//    private val newsArticles: MutableLiveData<List<NewsArticle>> by lazy {
    // Not sure about the order here
//    private val newsArticles: MutableLiveData<Resource<List<NewsArticle>>>? = null
    private val newsArticles: MutableLiveData<Resource<List<NewsArticle>>> by lazy {
        MutableLiveData<Resource<List<NewsArticle>>>().also {
            loadData()
        }
    }

    // Could be recursive?
    private fun loadData() {
        println("LoadData()")
//        newsArticles.postValue(Resource(Resource.Status.LOADING))
        disposables.add(newsArticleRepository.getNewsArticles()
            .subscribeOn(Schedulers.io())
            .subscribe({
                newsArticles.postValue(Resource(Resource.Status.SUCCESS, it))
            }, {
                newsArticles.postValue(Resource(Resource.Status.ERROR))
            }))
    }

    fun refreshData() {
        newsArticles.postValue(Resource(Resource.Status.LOADING))
        loadData()
    }

    fun getNewsArticles(): LiveData<Resource<List<NewsArticle>>> {
        return newsArticles
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}