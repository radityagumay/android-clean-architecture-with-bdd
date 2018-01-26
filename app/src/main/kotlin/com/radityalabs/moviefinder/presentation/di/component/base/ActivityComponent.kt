package com.radityalabs.moviefinder.presentation.di.component.base

import android.app.Activity
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.base.ActivityModule
import com.radityalabs.moviefinder.presentation.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)],
        modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun activity(): Activity

    fun navigator(): Navigator?
}