package com.radityalabs.moviefinder.presentation.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.radityalabs.moviefinder.presentation.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    @FragmentScope
    fun provideActivity(): Activity = fragment.activity!!
}
