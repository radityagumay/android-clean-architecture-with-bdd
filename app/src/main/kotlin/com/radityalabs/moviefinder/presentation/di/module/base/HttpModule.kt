package com.radityalabs.moviefinder.presentation.di.module.base

import com.google.gson.Gson
import com.radityalabs.moviefinder.BuildConfig
import com.radityalabs.moviefinder.data.network.HeaderInterceptor
import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.presentation.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class HttpModule {
    companion object {
        const val TIME = 30L
    }

    @Provides
    @AppScope
    fun gson() = Gson()

    @Provides
    @AppScope
    fun okHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @AppScope
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return (retrofit2.Retrofit.Builder()).baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @AppScope
    fun okHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpBuilder.addInterceptor(HeaderInterceptor())
        okHttpBuilder.connectTimeout(TIME, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIME, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(TIME, TimeUnit.SECONDS)
        okHttpBuilder.retryOnConnectionFailure(true)
        return okHttpBuilder.build()
    }

    @Provides
    @AppScope
    fun restService(retrofit: Retrofit): RestService =
            retrofit.create(RestService::class.java)
}