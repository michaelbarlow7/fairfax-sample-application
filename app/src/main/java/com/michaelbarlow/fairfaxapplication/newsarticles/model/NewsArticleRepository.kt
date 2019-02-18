package com.michaelbarlow.fairfaxapplication.newsarticles.model

import io.reactivex.Single

/**
 * Basic class that handles retrieving data for news articles.
 *
 * Refines the data a little bit (i.e. returns list of NewsArticles rather than raw data)
 * and sorts so that latest articles appear first
 */
class NewsArticleRepository(private val fairfaxAPIService: FairfaxAPIService) {

    fun getNewsArticles(): Single<List<NewsArticle>> {
        return fairfaxAPIService.getNewsArticles()
            .map {
                it.assets.sortedByDescending { t -> t.timeStamp }
            }
    }
}