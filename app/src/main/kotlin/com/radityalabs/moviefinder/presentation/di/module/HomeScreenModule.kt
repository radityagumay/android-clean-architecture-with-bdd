package com.radityalabs.moviefinder.presentation.di.module

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.repository.HomeDataStore
import com.radityalabs.moviefinder.data.repository.HomeRepository
import com.radityalabs.moviefinder.domain.HomeInteractor
import com.radityalabs.moviefinder.domain.HomeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeScreenModule {
    @Provides
    @Singleton
    fun repository(restService: RestService): HomeRepository = HomeDataStore(restService)

    @Provides
    @Singleton
    fun usecase(repository: HomeRepository): HomeUseCase = HomeInteractor(repository)
}