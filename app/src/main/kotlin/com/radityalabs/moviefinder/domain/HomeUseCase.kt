package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.repository.HomeRepository
import io.reactivex.Single
import javax.inject.Inject

interface HomeUseCase {
    fun fetchMovies(): Single<List<Boolean>>
}

class HomeInteractor @Inject constructor(private val repository: HomeRepository) : HomeUseCase {
    override fun fetchMovies(): Single<List<Boolean>> {
        return repository.fetchMovies()
    }
}