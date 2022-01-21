package com.zainalfn.moviecatalogue.data.source.remote

/**
 * Created by zainal on 1/17/22 - 7:42 AM
 */
import com.zainalfn.moviecatalogue.BuildConfig.API_KEY
import com.zainalfn.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.zainalfn.moviecatalogue.data.source.remote.response.MoviesResponse
import com.zainalfn.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.moviecatalogue.data.source.remote.response.TvShowsResponse
import com.zainalfn.moviecatalogue.network.RetrofitConfig
import com.zainalfn.moviecatalogue.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource {

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = apiService().getMovies(API_KEY)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                callback.onMoviesLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMovieCallback, movieId: String) {
        EspressoIdlingResource.increment()
        val client = apiService().getMovieDetail(movieId, API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                callback.onDetailMovieLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = apiService().getTvShows(API_KEY)
        client.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                callback.onTvShowsLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        EspressoIdlingResource.increment()
        val client = apiService().getTvShowDetail(tvShowId, API_KEY)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                callback.onDetailTvShowLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun apiService() = RetrofitConfig.apiInstance

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: ArrayList<MovieDetailResponse>?)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows: ArrayList<TvShowDetailResponse>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}