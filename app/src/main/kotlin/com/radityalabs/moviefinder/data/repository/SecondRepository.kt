package com.radityalabs.moviefinder.data.repository

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.model.response.MovieDetail
import com.radityalabs.moviefinder.external.singleIo
import io.reactivex.Single
import javax.inject.Inject

interface SecondRepository {
    fun getMovieById(movieId: Int): Single<MovieDetail.Response>
}

class SecondDataStore @Inject constructor(private val service: RestService) : SecondRepository {
    override fun getMovieById(movieId: Int): Single<MovieDetail.Response> {
        return service.getMovieById(movieId).compose(singleIo())
    }
}