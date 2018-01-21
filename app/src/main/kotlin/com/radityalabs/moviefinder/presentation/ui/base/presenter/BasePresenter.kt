package com.radityalabs.moviefinder.presentation.ui.base.presenter

import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : BaseView> {
    lateinit protected var view: V

    private val mCompositeDisposable by lazy {
        CompositeDisposable()
    }

    private fun dispose() {
        if (mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear()
        }
    }

    protected fun addDisposable(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        dispose()
    }
}

