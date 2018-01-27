package com.radityalabs.moviefinder.data

import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.data.model.response.MovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {
    @GET(RestConstant.discover)
    fun fetchMovies(
            @Query("page") page: Int
    ): Single<Discover.Response>

    @GET(RestConstant.movieDetail)
    fun getMovieById(
            @Path("movie_id") movieId: Int
    ): Single<MovieDetail.Response>
}