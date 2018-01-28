package com.radityalabs.moviefinder.presentation.di.module

import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.repository.ThridDataStore
import com.radityalabs.moviefinder.data.repository.ThridRepository
import com.radityalabs.moviefinder.domain.ThridInteractor
import com.radityalabs.moviefinder.domain.ThridUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ThridScreenModule {
    @Provides
    @Singleton
    fun repository(restService: RestService): ThridRepository = ThridDataStore(restService)

    @Provides
    @Singleton
    fun usecase(repository: ThridRepository): ThridUseCase = ThridInteractor(repository)
}