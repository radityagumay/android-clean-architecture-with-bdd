package com.radityalabs.moviefinder.presentation.di.component

import com.radityalabs.moviefinder.data.repository.ThridRepository
import com.radityalabs.moviefinder.presentation.di.module.ThridScreenModule
import com.radityalabs.moviefinder.presentation.ui.feature.ThridScreen
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(
        ThridScreenModule::class
))
interface ThridScreenComponent {
    fun inject(screen: ThridScreen)

    fun repository(): ThridRepository
}