package com.radityalabs.moviefinder.presentation.di.component

import com.radityalabs.moviefinder.data.repository.SecondRepository
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.ui.feature.MovieDetailScreen
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(
        SecondScreenModule::class
))
interface SecondScreenComponent {
    fun inject(screen: MovieDetailScreen)

    fun repository(): SecondRepository
}