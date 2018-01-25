package com.radityalabs.moviefinder.presentation.ui.feature

import android.widget.RelativeLayout
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.external.appComponent
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.ui.base.activity.BaseActivity
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.View>() {
    interface View : BaseView
}

class MainActivity : BaseActivity<MainPresenter.View, MainPresenter>() {
    internal var navigator: Navigator? = null
        @Inject set

    override fun setupInjection() {
        appComponent().inject(this)
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun setupView() {
        setSupportActionBar(toolbar)
    }

    override fun setupData() {
    }

    override fun createNavigator() = Navigator().apply {
        setRootNavigator(HomeScreen(this@MainActivity))
        setContainerNavigator(findViewById<RelativeLayout>(R.id.container))
    }
}
