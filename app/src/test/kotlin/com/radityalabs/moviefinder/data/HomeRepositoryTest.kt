package com.radityalabs.moviefinder.data

import com.nhaarman.mockito_kotlin.mock
import com.radityalabs.moviefinder.Fixture
import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.data.network.RestService
import com.radityalabs.moviefinder.data.repository.HomeRepository
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.verify

class HomeRepositoryTest : Spek({
    given("a home repository") {

        var repository = mock<HomeRepository>()
        var apiMock = mock<RestService>()
        var testSub = TestSubscriber<Discover.Response>()
        var callMock = mock<Single<Discover.Response>>()

        beforeEachTest {
            testSub = TestSubscriber()
            apiMock = mock()
            repository = mock()
            callMock = mock()
        }

        on("fetch movie list return 20 list movies") {
            it("should return list of movies") {
                val movieResponse = Fixture(Discover.Response::class.java, "response_fetch_movie.json").load()
                org.mockito.BDDMockito.given(repository.fetchMovies(1)).willReturn(Single.just(movieResponse))

                repository.fetchMovies(1)

                verify(repository).fetchMovies(1)
            }
        }

        on("fetch movies by date") {
            it("should return list of movies"){
                val movieResponse = Fixture(Discover.Response::class.java, "response_fetch_movie.json").load()
                org.mockito.BDDMockito.given(repository.fetchMoviesByDate("2801207")).willReturn(Single.just(movieResponse))

                repository.fetchMovies(1)

                verify(repository).fetchMovies(1)
            }
        }
    }
})