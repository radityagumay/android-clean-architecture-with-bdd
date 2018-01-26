package com.radityalabs.moviefinder.presentation.ui.base.activity

import android.os.Bundle
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : SingleActivity(), BaseView {
    @Inject
    lateinit var presenter: P

    var isSafe: Boolean = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
        setupData()
    }

    override fun onResume() {
        super.onResume()
        isSafe = true
    }

    override fun onPause() {
        super.onPause()
        isSafe = false
        presenter.detachView()
    }

    protected abstract fun setupData()
}
