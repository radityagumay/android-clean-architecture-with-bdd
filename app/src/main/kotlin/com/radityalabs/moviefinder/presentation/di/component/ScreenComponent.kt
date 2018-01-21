package com.radityalabs.moviefinder.presentation.di.component

import android.content.Context
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.di.module.ScreenModule
import com.radityalabs.moviefinder.presentation.di.scope.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = arrayOf(
        AppComponent::class
), modules = arrayOf(
        ScreenModule::class
))
interface ScreenComponent {
    fun context(): Context

    fun plus(module: HomeScreenModule): HomeScreenComponent
}