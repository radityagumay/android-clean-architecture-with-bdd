package com.radityalabs.moviefinder.presentation.ui.feature

import android.widget.TextView
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.presentation.ui.base.activity.BaseNoInjectionActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseNoInjectionActivity() {
    override fun getLayoutId() = R.layout.activity_main

    override fun setupView() {
        setSupportActionBar(toolbar)
        setupRootView()
    }

    private fun setupRootView() {
        container.addView(TextView(this))
    }
}
