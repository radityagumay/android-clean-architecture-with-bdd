package com.radityalabs.moviefinder.presentation.di.component

import android.app.Activity
import com.radityalabs.moviefinder.presentation.di.module.FragmentModule
import com.radityalabs.moviefinder.presentation.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = arrayOf(
        AppComponent::class
), modules = arrayOf(
        FragmentModule::class
))
interface FragmentComponent {
    fun activity(): Activity
}
