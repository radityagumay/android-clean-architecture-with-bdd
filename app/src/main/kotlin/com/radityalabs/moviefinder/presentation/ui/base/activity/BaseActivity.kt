package com.radityalabs.moviefinder.presentation.ui.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.radityalabs.moviefinder.external.appComponent
import com.radityalabs.moviefinder.presentation.di.component.ActivityComponent
import com.radityalabs.moviefinder.presentation.di.component.AppComponent
import com.radityalabs.moviefinder.presentation.di.component.DaggerActivityComponent
import com.radityalabs.moviefinder.presentation.di.component.DaggerAppComponent
import com.radityalabs.moviefinder.presentation.di.module.ActivityModule
import com.radityalabs.moviefinder.presentation.di.module.AppModule
import com.radityalabs.moviefinder.presentation.di.module.HttpModule
import com.radityalabs.moviefinder.presentation.ui.App
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : SingleActivity(), BaseView {
    @Inject
    lateinit var presenter: P

    var isSafe: Boolean = false
        private set

    protected val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(appComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
        presenter.attachView(this as V)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupView()
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

    protected abstract fun setupInjection()

    protected abstract fun setupView()

    protected abstract fun setupData()
}
