package com.michaelbarlow.fairfaxapplication

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.*
import com.michaelbarlow.fairfaxapplication.newsarticles.model.NewsArticle
import com.michaelbarlow.fairfaxapplication.newsarticles.model.NewsArticleRepository
import com.michaelbarlow.fairfaxapplication.newsarticles.viewmodel.NewsArticleViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NewsArticleViewModelUnitTest {

    /**
     *  A custom observer for testing LiveData changes. It sets the lifecycle to "ON_RESUME",
     *  and then calls the provided handler lambda, in which we can test assertions
     */
    class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
        private val lifecycle = LifecycleRegistry(this)
        init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        override fun getLifecycle(): Lifecycle = lifecycle

        override fun onChanged(t: T?) {
            handler(t!!)
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }
    }

    /**
     * An extension function for LiveData to allow for easy testing
     */
    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    /**
     *  This rule ensures all operations run on the same thread.
     */
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsArticleRepository: NewsArticleRepository

    lateinit var newsArticleViewModel: NewsArticleViewModel

    private val mockSuccessArticleList by lazy {
        val firstArticle = NewsArticle()
        listOf(firstArticle)
    }

    @Before
    fun setup() {
        newsArticleViewModel =
            NewsArticleViewModel(newsArticleRepository)
    }

    @Test
    fun successResponse_isCorrectFormat() {
        Mockito.`when`(newsArticleRepository.getNewsArticles())
            .thenReturn(Single.just(mockSuccessArticleList))
        val newsArticles = newsArticleViewModel.getNewsArticles()
        newsArticles.observeOnce {
            Assert.assertSame(it.status, Resource.Status.SUCCESS)
            Assert.assertSame(it.data, mockSuccessArticleList)
        }
    }

    @Test
    fun errorResponse_returnsErrorStatus() {
        Mockito.`when`(newsArticleRepository.getNewsArticles())
            .thenReturn(Single.error(Exception()))
        val newsArticles = newsArticleViewModel.getNewsArticles()

        newsArticles.observeOnce {
            Assert.assertSame(it.status, Resource.Status.ERROR)
            Assert.assertSame(it.data, null)
        }
    }

}
