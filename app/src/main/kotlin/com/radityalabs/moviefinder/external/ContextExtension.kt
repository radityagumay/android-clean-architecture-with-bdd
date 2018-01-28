package com.radityalabs.moviefinder.external

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}