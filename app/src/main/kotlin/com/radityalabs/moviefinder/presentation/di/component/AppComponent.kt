package com.radityalabs.moviefinder.presentation.di.component

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.presentation.di.module.AppModule
import com.radityalabs.moviefinder.presentation.di.module.HttpModule
import com.radityalabs.moviefinder.presentation.di.scope.AppScope
import com.radityalabs.moviefinder.presentation.ui.App
import dagger.Component

@AppScope
@Component(modules = arrayOf(
        AppModule::class,
        HttpModule::class
))
interface AppComponent {
    fun app(): App

    fun restService(): RestService
}