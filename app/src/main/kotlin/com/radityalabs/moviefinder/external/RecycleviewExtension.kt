package com.radityalabs.moviefinder.external

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import com.radityalabs.moviefinder.presentation.ui.widget.DividerSpacingItemDecoration
import com.radityalabs.moviefinder.presentation.ui.widget.GridSpacingItemDecoration

fun RecyclerView.vertical(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    return this
}

fun RecyclerView.horizontal(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    return this
}

fun RecyclerView.grid(spanCount: Int = 2): RecyclerView {
    layoutManager = GridLayoutManager(context, spanCount)
    addItemDecoration(GridSpacingItemDecoration(spanCount, toDp(context, 9f), false))
    return this
}

fun RecyclerView.dividerDecorator(marginLeft: Int = 16, marginRight: Int = 16): RecyclerView {
    addItemDecoration(DividerSpacingItemDecoration(context, marginLeft = toDp(context, marginLeft.toFloat()), marginRight = toDp(context, marginRight.toFloat())))
    return this
}

fun RecyclerView.animator(): RecyclerView {
    itemAnimator = DefaultItemAnimator()
    return this
}

fun toDp(context: Context, dp: Float): Int {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}
