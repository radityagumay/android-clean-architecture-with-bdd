package com.radityalabs.moviefinder.presentation.ui.feature

import android.content.Context
import android.view.LayoutInflater
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.di.module.ThridScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.screen_home.view.*
import javax.inject.Inject

class ThridScreen(context: Context) : BaseScreen<ThridScreenPresenter.View, ThridScreenPresenter>(context),
        ThridScreenPresenter.View {

    internal var navigator: Navigator? = null
        @Inject set

    init {
        LayoutInflater.from(context).inflate(R.layout.thrid_home, this, true)
    }

    override fun setupInjection() {
        screenComponent.plus(ThridScreenModule()).inject(this)
    }

    override fun setupEvent() {
    }

    override fun setupView() {
        button.setOnClickListener {
            navigator?.goBack()
        }
    }

    override fun setupData() {
    }
}

class ThridScreenPresenter @Inject constructor() : BasePresenter<ThridScreenPresenter.View>() {
    interface View : BaseView
}