package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.model.entity.MovieDetailViewEntity
import com.radityalabs.moviefinder.data.repository.SecondRepository
import io.reactivex.Single
import javax.inject.Inject

interface SecondUseCase {
    fun getMovieById(movieId: Int): Single<MovieDetailViewEntity>
}

class SecondInteractor @Inject constructor(private val repository: SecondRepository) : SecondUseCase {
    override fun getMovieById(movieId: Int): Single<MovieDetailViewEntity> {
        return repository.getMovieById(movieId).flatMap { movie ->
            val genre = StringBuilder()
            val size = movie.genres?.size ?: 0
            for (i in 0 until size) {
                genre.append(movie.genres?.get(i)?.name)
                genre.append(", ")
            }
            val viewEntity = MovieDetailViewEntity(
                    youtubeKey = movie.videos?.results?.get(0)?.key,
                    overview = movie.overview,
                    genre = genre.toString().trimEnd(',', ' '),
                    title = movie.originalTitle,
                    posterImage = "https://image.tmdb.org/t/p/w1280/${movie.posterPath}")
            Single.just(viewEntity)
        }
    }
}