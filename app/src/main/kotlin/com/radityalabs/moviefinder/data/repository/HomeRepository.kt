package com.radityalabs.moviefinder.data.repository

import com.radityalabs.moviefinder.data.RestService
import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.external.singleIo
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {
    fun fetchMovies(page: Int): Single<Discover.Response>
}

class HomeDataStore @Inject constructor(private val service: RestService) : HomeRepository {
    override fun fetchMovies(page: Int): Single<Discover.Response> {
        return service.fetchMovies(page).compose(singleIo())
    }
}