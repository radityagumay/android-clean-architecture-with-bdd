package com.radityalabs.moviefinder.data.repository

import com.radityalabs.moviefinder.data.network.RestService
import io.reactivex.Single
import javax.inject.Inject

interface ThridRepository {
    fun fetchMovies(): Single<List<Boolean>>
}

class ThridDataStore @Inject constructor(private val service: RestService) : ThridRepository {
    override fun fetchMovies(): Single<List<Boolean>> {
        return service.fetchMovies()
    }
}