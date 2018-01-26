package com.radityalabs.moviefinder.presentation.ui.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import com.radityalabs.moviefinder.external.appComponent
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.component.base.ActivityComponent
import com.radityalabs.moviefinder.presentation.di.component.base.DaggerActivityComponent
import com.radityalabs.moviefinder.presentation.di.module.base.ActivityModule
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen
import javax.inject.Inject

abstract class SingleActivity : AppCompatActivity() {
    protected var navigator: Navigator? = null
        @Inject set

    protected val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(appComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        setupInjection()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()

        val layout = getViewLayout() as RelativeLayout
        val screen = getRootView()
        setupView(layout, screen)

        navigator?.setRootNavigator(screen)
        navigator?.setContainerNavigator(layout)

        navigator(navigator)
    }

    protected abstract fun setupInjection()

    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewLayout(): View

    protected abstract fun getRootView(): Screen

    protected abstract fun initView()

    protected abstract fun navigator(navigator: Navigator?)

    private fun setupView(view: RelativeLayout, screen: Screen) {
        view.addView(screen)
    }
}
