package com.michaelbarlow.fairfaxapplication

import io.reactivex.Single

class NewsArticleRepository(private val fairfaxAPIService:FairfaxAPIService) {

    fun getNewsArticles(): Single<List<NewsArticle>> {
        return fairfaxAPIService.getNewsArticles()
            .map {
                it.assets
            }
    }
}