package com.radityalabs.moviefinder.presentation.di.component

import com.radityalabs.moviefinder.data.repository.HomeRepository
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.ui.feature.HomeScreen
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(
        HomeScreenModule::class
))
interface HomeScreenComponent {
    fun inject(screen: HomeScreen)

    fun repository(): HomeRepository
}