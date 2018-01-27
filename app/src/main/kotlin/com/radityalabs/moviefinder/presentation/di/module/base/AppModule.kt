package com.radityalabs.moviefinder.presentation.di.module.base

import com.radityalabs.moviefinder.data.RestService
import com.radityalabs.moviefinder.data.RetrofitHelper
import com.radityalabs.moviefinder.external.navigator.Navigator
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

    @Provides
    @AppScope
    fun provideNavigator() = Navigator.getInstance()
}