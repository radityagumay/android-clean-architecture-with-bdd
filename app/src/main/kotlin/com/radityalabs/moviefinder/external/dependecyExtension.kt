package com.radityalabs.moviefinder.external

import android.content.Context
import android.support.v4.app.Fragment
import com.radityalabs.moviefinder.presentation.di.component.AppComponent
import com.radityalabs.moviefinder.presentation.di.component.DaggerAppComponent
import com.radityalabs.moviefinder.presentation.di.module.AppModule
import com.radityalabs.moviefinder.presentation.di.module.HttpModule
import com.radityalabs.moviefinder.presentation.ui.App

fun Fragment.appComponent(): AppComponent = this.context!!.appComponent()

fun Context.appComponent(): AppComponent {
    val lifeComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this.applicationContext as App))
                .httpModule(HttpModule())
                .build()
    }
    return lifeComponent
}