package com.radityalabs.moviefinder.presentation.di.module

import android.content.Context
import android.view.View
import com.radityalabs.moviefinder.presentation.di.scope.ScreenScope
import dagger.Module
import dagger.Provides

@Module
class ScreenModule(private val view: View) {
    @Provides
    @ScreenScope
    fun activity(): Context = view.context
}