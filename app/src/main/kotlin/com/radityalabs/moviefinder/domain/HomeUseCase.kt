package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.data.repository.HomeRepository
import io.reactivex.Single
import javax.inject.Inject

interface HomeUseCase {
    fun fetchMovies(page: Int): Single<List<Discover.Result>>

    fun fetchMoviesByDate(dayOfMonth: Int, monthOfYear: Int, year: Int): Single<List<Discover.Result>>
}

class HomeInteractor @Inject constructor(private val repository: HomeRepository) : HomeUseCase {
    override fun fetchMoviesByDate(dayOfMonth: Int, monthOfYear: Int, year: Int): Single<List<Discover.Result>> {
        return repository.fetchMoviesByDate("$dayOfMonth$monthOfYear$year").flatMap { item ->
            val size = item.results?.size ?: 0
            if (size > 0) {
                val entities = mutableListOf<Discover.Result>()
                item.results?.forEach { it ->
                    entities.add(Discover.Result(
                            voteCount = it?.voteCount,
                            id = it?.id,
                            video = it?.video,
                            voteAverage = it?.voteAverage,
                            title = it?.title,
                            popularity = it?.popularity,
                            posterPath = it?.posterPath,
                            originalLanguage = it?.originalLanguage,
                            originalTitle = it?.originalTitle,
                            genreIds = it?.genreIds,
                            backdropPath = it?.backdropPath,
                            adult = it?.adult,
                            overview = it?.overview,
                            releaseDate = it?.releaseDate
                    ))
                }
                Single.just(entities)
            } else {
                Single.just(mutableListOf())
            }
        }
    }

    override fun fetchMovies(page: Int): Single<List<Discover.Result>> {
        return repository.fetchMovies(page).flatMap { item ->
            val size = item.results?.size ?: 0
            if (size > 0) {
                val entities = mutableListOf<Discover.Result>()
                item.results?.forEach { it ->
                    entities.add(Discover.Result(
                            voteCount = it?.voteCount,
                            id = it?.id,
                            video = it?.video,
                            voteAverage = it?.voteAverage,
                            title = it?.title,
                            popularity = it?.popularity,
                            posterPath = it?.posterPath,
                            originalLanguage = it?.originalLanguage,
                            originalTitle = it?.originalTitle,
                            genreIds = it?.genreIds,
                            backdropPath = it?.backdropPath,
                            adult = it?.adult,
                            overview = it?.overview,
                            releaseDate = it?.releaseDate
                    ))
                }
                Single.just(entities)
            } else {
                Single.just(mutableListOf())
            }
        }
    }
}