package com.radityalabs.moviefinder.domain

import com.radityalabs.moviefinder.data.repository.SecondRepository
import io.reactivex.Single
import javax.inject.Inject

interface SecondUseCase {
    fun fetchMovies(): Single<List<Boolean>>
}

class SecondInteractor @Inject constructor(private val repository: SecondRepository) : SecondUseCase {
    override fun fetchMovies(): Single<List<Boolean>> {
        return repository.fetchMovies()
    }
}