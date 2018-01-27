package com.radityalabs.moviefinder.external

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.radityalabs.moviefinder.presentation.di.module.GlideApp

fun String.loadImage(context: Context,
                     resId: ImageView) {
    GlideApp.with(context)
            .load(this)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(200, 200)
            .centerCrop()
            .into(resId)
}
