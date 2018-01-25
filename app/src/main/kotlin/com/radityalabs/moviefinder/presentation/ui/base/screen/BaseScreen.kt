package com.radityalabs.moviefinder.presentation.ui.base.screen

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.radityalabs.moviefinder.external.appComponent
import com.radityalabs.moviefinder.presentation.di.component.DaggerScreenComponent
import com.radityalabs.moviefinder.presentation.di.component.ScreenComponent
import com.radityalabs.moviefinder.presentation.di.module.ScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseScreen<V : BaseView, P : BasePresenter<V>>(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0) : Screen(context, attributeSet, defStyle),
        BaseView {

    @Inject
    lateinit var presenter: P

    protected val screenComponent: ScreenComponent by lazy {
        DaggerScreenComponent.builder()
                .appComponent(context.appComponent())
                .screenModule(ScreenModule(this))
                .build()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupInjection()
        presenter.attachView(this as V)
        setupEvent()
        setupView()
        setupData()
    }

    override fun onDetachedFromWindow() {
        presenter.detachView()
        super.onDetachedFromWindow()
    }

    abstract fun setupInjection()

    abstract fun setupEvent()

    abstract fun setupView()

    abstract fun setupData()
}