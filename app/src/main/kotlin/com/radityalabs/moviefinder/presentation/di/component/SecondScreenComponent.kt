package com.radityalabs.moviefinder.presentation.di.component

import com.radityalabs.moviefinder.data.repository.SecondRepository
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.ui.feature.SecondScreen
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(
        SecondScreenModule::class
))
interface SecondScreenComponent {
    fun inject(screen: SecondScreen)

    fun repository(): SecondRepository
}