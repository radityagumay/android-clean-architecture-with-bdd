package com.radityalabs.moviefinder.data.repository

import com.radityalabs.moviefinder.data.network.RestService
import io.reactivex.Single
import javax.inject.Inject

interface SecondRepository {
    fun fetchMovies(): Single<List<Boolean>>
}

class SecondDataStore @Inject constructor(private val service: RestService) : SecondRepository {
    override fun fetchMovies(): Single<List<Boolean>> {
        return service.fetchMovies()
    }
}