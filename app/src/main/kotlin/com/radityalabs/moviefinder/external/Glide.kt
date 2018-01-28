package com.radityalabs.moviefinder.external

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.radityalabs.moviefinder.presentation.di.module.GlideApp
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun String.loadImage(context: Context,
                     resId: ImageView) {
    GlideApp.with(context)
            .load(this)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(resId)
}

fun String.loadAsBitmap(context: Context,
                        resId: ImageView,
                        block: (Palette) -> Unit) {
    Picasso.with(context).load(this).into(resId, object : Callback {
        override fun onSuccess() {
            val bitmap = (resId.drawable as BitmapDrawable).bitmap
            Palette.from(bitmap).generate { palette -> block(palette) }
        }

        override fun onError() {
        }
    })
}
