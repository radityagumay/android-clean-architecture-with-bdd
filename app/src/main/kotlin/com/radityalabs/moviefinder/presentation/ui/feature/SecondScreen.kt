package com.radityalabs.moviefinder.presentation.ui.feature

import android.content.Context
import android.view.LayoutInflater
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.second_home.view.*
import javax.inject.Inject

class SecondScreen(context: Context) : BaseScreen<SecondScreenPresenter.View, SecondScreenPresenter>(context),
        SecondScreenPresenter.View {

    internal var navigator: Navigator? = null
        @Inject set

    init {
        LayoutInflater.from(context).inflate(R.layout.second_home, this, true)
    }

    override fun setupInjection() {
        screenComponent.plus(SecondScreenModule()).inject(this)
    }

    override fun setupEvent() {
    }

    override fun setupView() {
        forward.setOnClickListener {
            navigator?.goTo(ThridScreen(context))
        }

        back.setOnClickListener {
            navigator?.goBack()
        }
    }

    override fun setupData() {
    }
}

class SecondScreenPresenter @Inject constructor() : BasePresenter<SecondScreenPresenter.View>() {
    interface View : BaseView
}