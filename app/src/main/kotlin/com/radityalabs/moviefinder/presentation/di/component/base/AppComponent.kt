package com.radityalabs.moviefinder.presentation.di.component.base

import com.radityalabs.moviefinder.data.RestService
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.base.AppModule
import com.radityalabs.moviefinder.presentation.di.module.base.HttpModule
import com.radityalabs.moviefinder.presentation.di.scope.AppScope
import com.radityalabs.moviefinder.presentation.ui.App
import com.radityalabs.moviefinder.presentation.ui.base.activity.SingleActivity
import com.radityalabs.moviefinder.presentation.ui.feature.MainActivity
import dagger.Component

@AppScope
@Component(modules = arrayOf(
        AppModule::class,
        HttpModule::class
))
interface AppComponent {
    fun app(): App

    fun restService(): RestService

    fun navigator(): Navigator?

    fun inject(activity: MainActivity)

    fun inject(activity: SingleActivity)
}