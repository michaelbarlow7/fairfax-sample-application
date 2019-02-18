package com.michaelbarlow.fairfaxapplication

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Interface to be used with RetroFit
 */
interface FairfaxAPIService {

    @GET("1/coding_test/13ZZQX/full")
    fun getNewsArticles(): Single<APIResponse>
}