package com.michaelbarlow.fairfaxapplication.newsarticles.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.michaelbarlow.fairfaxapplication.Resource
import com.michaelbarlow.fairfaxapplication.newsarticles.model.NewsArticle
import com.michaelbarlow.fairfaxapplication.newsarticles.model.NewsArticleRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel for News Articles
 */
class NewsArticleViewModel(private val newsArticleRepository: NewsArticleRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    /**
     * When the News Articles livedata is first accessed, we load the data from the
     * repository in an attempt to populate it.
     */
    private val newsArticles: MutableLiveData<Resource<List<NewsArticle>>> by lazy {
        MutableLiveData<Resource<List<NewsArticle>>>().also {
            loadData()
        }
    }

    private fun loadData() {
        disposables.add(newsArticleRepository.getNewsArticles()
            .subscribeOn(Schedulers.io())
            .subscribe({
                newsArticles.postValue(
                    Resource(
                        Resource.Status.SUCCESS,
                        it
                    )
                )
            }, {
                newsArticles.postValue(Resource(Resource.Status.ERROR))
            }))
    }

    fun getNewsArticles(): LiveData<Resource<List<NewsArticle>>> {
        return newsArticles
    }

    /**
     * Any RxJava observables are cleared when the LiveData is cleared
     */
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}