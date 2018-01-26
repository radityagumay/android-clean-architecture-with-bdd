package com.radityalabs.moviefinder.presentation.ui.feature

import android.content.Context
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.external.appComponent
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.ui.base.activity.BaseActivity
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter.View, MainPresenter>() {

    override fun setupInjection() = appComponent().inject(this)

    override fun getViewLayout() = container

    override fun getRootView() = HomeScreen(this)

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() = setSupportActionBar(toolbar)

    override fun navigator(navigator: Navigator?) {
        navigator?.toObservable()?.subscribe { screen ->
            container.removeViewAt(0)
            container.addView(screen.factory(this))
        }
    }

    override fun setupData() {}

    inline fun <reified T : Screen> Screen.factory(context: Context): T =
            when (this) {
                is HomeScreen -> HomeScreen(context) as T
                is SecondScreen -> SecondScreen(context) as T
                is ThridScreen -> ThridScreen(context) as T
                else -> {
                    throw IllegalStateException("Screen are not define yet!")
                }
            }
}

class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.View>() {
    interface View : BaseView
}
