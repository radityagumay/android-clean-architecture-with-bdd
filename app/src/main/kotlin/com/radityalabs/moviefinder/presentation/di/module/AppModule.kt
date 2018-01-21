package com.radityalabs.moviefinder.presentation.di.module

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.network.RetrofitHelper
import com.radityalabs.moviefinder.presentation.di.scope.AppScope
import com.radityalabs.moviefinder.presentation.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    @AppScope
    fun provideAppContext() = app.applicationContext

    @Provides
    @AppScope
    fun provideApp() = app

    @Provides
    @AppScope
    fun provideRetrofitHelper(service: RestService) = RetrofitHelper(service)
}