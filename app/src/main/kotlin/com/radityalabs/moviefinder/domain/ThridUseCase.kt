package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.repository.ThridRepository
import io.reactivex.Single
import javax.inject.Inject

interface ThridUseCase {
    fun fetchMovies(): Single<List<Boolean>>
}

class ThridInteractor @Inject constructor(private val repository: ThridRepository) : ThridUseCase {
    override fun fetchMovies(): Single<List<Boolean>> {
        return repository.fetchMovies()
    }
}