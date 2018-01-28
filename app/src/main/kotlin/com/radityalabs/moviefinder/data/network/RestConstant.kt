package com.radityalabs.moviefinder.data.network

import com.radityalabs.moviefinder.BuildConfig

object RestConstant {
    const val api_key       = "api_key=${BuildConfig.API_KEY}"

    const val discover      = BuildConfig.BASE_URL + "discover/movie?sort_by=popularity.desc" + "&" + api_key
    const val movieDetail   = BuildConfig.BASE_URL + "movie/{movie_id}" + "?" + api_key
}