package com.michaelbarlow.fairfaxapplication

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsArticleRepositoryUnitTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var fairfaxAPIService: FairfaxAPIService

    lateinit var newsArticleRepository: NewsArticleRepository

    @Before
    fun setup() {
        newsArticleRepository = NewsArticleRepository(fairfaxAPIService)

        val mockSuccessArticleList by lazy {
            val firstArticle = NewsArticle()
            listOf(firstArticle)
        }

        val response = APIResponse(mockSuccessArticleList)

        Mockito.`when`(fairfaxAPIService.getNewsArticles())
            .thenReturn(Single.just(response))
    }

    @Test
    fun webAPI_isCalled(){
        val newsArticles = newsArticleRepository.getNewsArticles()
        val testObserver = TestObserver<List<NewsArticle>>()
        newsArticles.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        assertEquals(testObserver.values().size, 1)
    }
}