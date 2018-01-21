package com.radityalabs.moviefinder.presentation.di.module

import android.app.Activity
import com.radityalabs.moviefinder.presentation.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @ActivityScope
    fun activity(): Activity = activity
}
