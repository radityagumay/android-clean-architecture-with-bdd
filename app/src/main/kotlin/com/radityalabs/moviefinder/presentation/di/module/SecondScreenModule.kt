package com.radityalabs.moviefinder.presentation.di.module

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.repository.HomeDataStore
import com.radityalabs.moviefinder.data.repository.HomeRepository
import com.radityalabs.moviefinder.data.repository.SecondDataStore
import com.radityalabs.moviefinder.data.repository.SecondRepository
import com.radityalabs.moviefinder.domain.HomeInteractor
import com.radityalabs.moviefinder.domain.HomeUseCase
import com.radityalabs.moviefinder.domain.SecondInteractor
import com.radityalabs.moviefinder.domain.SecondUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SecondScreenModule {
    @Provides
    @Singleton
    fun repository(restService: RestService): SecondRepository = SecondDataStore(restService)

    @Provides
    @Singleton
    fun usecase(repository: SecondRepository): SecondUseCase = SecondInteractor(repository)
}