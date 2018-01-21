package com.radityalabs.moviefinder.presentation.ui.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseNoInjectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupView()
    }

    abstract fun getLayoutId(): Int

    abstract fun setupView()
}
