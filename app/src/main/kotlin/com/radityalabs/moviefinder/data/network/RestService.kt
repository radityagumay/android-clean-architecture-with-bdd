package com.radityalabs.moviefinder.data.network

import io.reactivex.Single

interface RestService {

    fun fetchMovies(): Single<List<Boolean>>
}