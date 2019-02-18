package com.michaelbarlow.fairfaxapplication

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verify


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class NewsArticleViewModelUnitTest {

    @Rule
    @JvmField
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
        newsArticleViewModel = NewsArticleViewModel(newsArticleRepository)
    }

    @Test
    fun successResponse_isCorrectFormat() {
        val observer: Observer<Resource<List<NewsArticle>>> = mock()
        Mockito.`when`(newsArticleRepository.getNewsArticles())
            .thenReturn(Single.just(mockSuccessArticleList))
        val newsArticles = newsArticleViewModel.getNewsArticles()
        newsArticles.observeForever(observer)
        verify(observer).onChanged(Resource(Resource.Status.SUCCESS, mockSuccessArticleList))
    }

    @Test
    fun errorResponse_returnsErrorStatus() {
        val observer: Observer<Resource<List<NewsArticle>>> = mock()
        Mockito.`when`(newsArticleRepository.getNewsArticles())
            .thenReturn(Single.error(Exception()))
        val newsArticles = newsArticleViewModel.getNewsArticles()
        newsArticles.observeForever(observer)
        verify(observer).onChanged(Resource(Resource.Status.ERROR, null))
    }

    @Test
    fun loadingTest_returnsLoadingStatus() {
        val observer: Observer<Resource<List<NewsArticle>>> = mock()
        Mockito.`when`(newsArticleRepository.getNewsArticles())
            .thenReturn(Single.just(mockSuccessArticleList))
        val newsArticles = newsArticleViewModel.getNewsArticles()
        newsArticles.observeForever(observer)
        newsArticleViewModel.refreshData()
        verify(observer).onChanged(Resource(Resource.Status.LOADING, null))
        verify(observer).onChanged(Resource(Resource.Status.SUCCESS, mockSuccessArticleList))

    }
}
