package com.yomi.latestnews.di

import com.google.gson.GsonBuilder
import com.yomi.latestnews.data.remote.NewsService
import com.yomi.latestnews.util.Endpoints
import com.yomi.latestnews.util.TIME_OUT
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(androidContext().cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(mCache)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(logging)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Api-Key", Endpoints.API_KEY)
                    .build()
                chain.proceed(newRequest)
            }.build()

        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Endpoints.BASE_URL).build()
    }

    factory { get<Retrofit>().create(NewsService::class.java) }
}