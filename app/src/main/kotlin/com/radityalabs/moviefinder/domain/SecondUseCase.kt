package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.model.response.MovieDetail
import com.radityalabs.moviefinder.data.repository.SecondRepository
import io.reactivex.Single
import javax.inject.Inject

interface SecondUseCase {
    fun getMovieById(movieId: Int): Single<MovieDetail.Response>
}

class SecondInteractor @Inject constructor(private val repository: SecondRepository) : SecondUseCase {
    override fun getMovieById(movieId: Int): Single<MovieDetail.Response> {
        return repository.getMovieById(movieId)
    }
}