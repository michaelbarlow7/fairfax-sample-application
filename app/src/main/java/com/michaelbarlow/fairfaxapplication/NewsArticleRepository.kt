package com.michaelbarlow.fairfaxapplication

import io.reactivex.Single

/**
 * Basic class that handles retrieving data for news articles.
 *
 * Refines the data a little bit (i.e. returns list of NewsArticles rather than raw data)
 */
class NewsArticleRepository(private val fairfaxAPIService:FairfaxAPIService) {

    fun getNewsArticles(): Single<List<NewsArticle>> {
        return fairfaxAPIService.getNewsArticles()
            .map {
                it.assets
            }
    }
}