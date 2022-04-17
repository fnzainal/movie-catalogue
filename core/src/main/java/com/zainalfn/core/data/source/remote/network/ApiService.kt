package com.zainalfn.core.data.source.remote.network

import com.zainalfn.core.data.source.remote.response.MovieDetailResponse
import com.zainalfn.core.data.source.remote.response.MoviesResponse
import com.zainalfn.core.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.core.data.source.remote.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): TvShowsResponse

    @GET("tv/{id}")
    suspend fun getTvShowDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): TvShowDetailResponse

}