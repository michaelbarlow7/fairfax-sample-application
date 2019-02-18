package com.michaelbarlow.fairfaxapplication

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FairfaxApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }

    val appModule = module {
        single {createNetworkClient()}

        single { NewsArticleRepository(get()) }

        viewModel { NewsArticleViewModel(get()) }
    }

    private fun createNetworkClient() : FairfaxAPIService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit =  Retrofit.Builder()
            .baseUrl("https://bruce-v2-mob.fairfaxmedia.com.au/")
//            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FairfaxAPIService::class.java)

    }
}