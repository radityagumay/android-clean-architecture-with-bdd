package com.radityalabs.moviefinder.presentation.ui.feature

import android.content.Context
import android.view.LayoutInflater
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.screen_home.view.*
import javax.inject.Inject

class HomeScreen(context: Context) : BaseScreen<HomeScreenPresenter.View, HomeScreenPresenter>(context),
        HomeScreenPresenter.View {

    internal var navigator: Navigator? = null
        @Inject set

    init {
        LayoutInflater.from(context).inflate(R.layout.screen_home, this, true)
    }

    override fun setupInjection() {
        screenComponent.plus(HomeScreenModule()).inject(this)
    }

    override fun setupEvent() {
    }

    override fun setupView() {
        button.setOnClickListener {
            navigator?.goTo(SecondScreen(context))
        }
    }

    override fun setupData() {
    }
}

class HomeScreenPresenter @Inject constructor() : BasePresenter<HomeScreenPresenter.View>() {
    interface View : BaseView
}