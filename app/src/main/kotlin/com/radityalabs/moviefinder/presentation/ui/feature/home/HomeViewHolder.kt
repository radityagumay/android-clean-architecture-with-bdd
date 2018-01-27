package com.radityalabs.moviefinder.presentation.ui.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.external.loadImage
import kotlinx.android.synthetic.main.list_home_item.view.*

class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun inflate(parent: ViewGroup?) =
                HomeViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.list_home_item, parent, false))
    }

    val image = view.image
    val overview = view.overview
    val title = view.title
    val container = view.container

    fun bind(item: Discover.Result) {
        val fullpath = "https://image.tmdb.org/t/p/w1280/${item.backdropPath}"
        fullpath.loadImage(view.context, image)

        overview.text = item.overview
        title.text = item.title
    }
}