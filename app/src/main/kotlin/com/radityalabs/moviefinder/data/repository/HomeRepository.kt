package com.radityalabs.moviefinder.data.repository

import com.radityalabs.moviefinder.data.network.RestService
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {
    fun fetchMovies(): Single<List<Boolean>>
}

class HomeDataStore @Inject constructor(private val service: RestService) : HomeRepository {
    override fun fetchMovies(): Single<List<Boolean>> {
        return service.fetchMovies()
    }
}