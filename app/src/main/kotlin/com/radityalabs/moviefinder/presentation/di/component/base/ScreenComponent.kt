package com.radityalabs.moviefinder.presentation.di.component.base

import android.content.Context
import com.radityalabs.moviefinder.presentation.di.component.HomeScreenComponent
import com.radityalabs.moviefinder.presentation.di.component.SecondScreenComponent
import com.radityalabs.moviefinder.presentation.di.component.ThridScreenComponent
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.di.module.ThridScreenModule
import com.radityalabs.moviefinder.presentation.di.module.base.ScreenModule
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

    fun plus(module: SecondScreenModule): SecondScreenComponent

    fun plus(module: ThridScreenModule): ThridScreenComponent
}