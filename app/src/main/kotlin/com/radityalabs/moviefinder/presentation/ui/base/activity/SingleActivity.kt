package com.radityalabs.moviefinder.presentation.ui.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen

abstract class SingleActivity : AppCompatActivity() {

    private var navigator: Navigator? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        setContentView(getLayoutId())
        super.onPostCreate(savedInstanceState)
        setupNavigator()
        setupView((navigator?.container as RelativeLayout))
    }

    protected abstract fun createNavigator(): Navigator

    protected abstract fun getLayoutId(): Int

    fun setupView(view: ViewGroup) {
        view.addView(navigator?.root as Screen)
    }

    private fun setupNavigator() {
        if (navigator == null) {
            navigator = createNavigator()
        }
    }
}
