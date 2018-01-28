package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.model.response.MovieDetail
import com.radityalabs.moviefinder.data.repository.SecondRepository
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import javax.inject.Inject

interface SecondUseCase {
    fun getMovieById(movieId: Int): Single<MovieDetail.Response>
}

class SecondInteractor @Inject constructor(private val repository: SecondRepository) : SecondUseCase {
    override fun getMovieById(movieId: Int): Single<MovieDetail.Response> {
        return repository.getMovieById(movieId).flatMap { movie ->
            val genre = StringBuilder()
            val size = movie.genres?.size ?: 0
            for (i in 0 until size) {
                genre.append(movie.genres?.get(i)?.name)
                genre.append(",")
            }
            val fullPoster = "https://image.tmdb.org/t/p/w1280/${movie.belongsToCollection?.posterPath}"
            movie.belongsToCollection?.copy(posterPath = fullPoster)
            movie.copy(appendedGenre = genre.toString())
            Single.just(movie)
        }
    }
}