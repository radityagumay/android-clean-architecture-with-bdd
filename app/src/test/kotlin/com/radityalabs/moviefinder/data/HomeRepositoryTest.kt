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
import org.mockito.Mockito.`when`

class HomeRepositoryTest : Spek({
    given("a home repository") {

        var repository              = mock<HomeRepository>()
        var apiMock                 = mock<RestService>()
        var testSub                 = TestSubscriber<Discover.Response>()
        var callMock                = mock<Single<Discover.Response>>()

        beforeEachTest {
            testSub                 = TestSubscriber()
            apiMock                 = mock()
            repository              = mock()
            callMock                = mock()
        }

        on("fetch movie list return 20 list movies") {
            it("should return list of movies") {
                val response = Fixture(Discover.Response::class.java, "response_fetch_movie.json").load()
                `when`(apiMock.fetchMovies(1)).thenReturn(callMock)
            }
        }
    }
})