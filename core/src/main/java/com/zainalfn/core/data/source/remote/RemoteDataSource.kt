package com.zainalfn.core.data.source.remote

/**
 * Created by zainal on 1/17/22 - 7:42 AM
 */
import com.zainalfn.core.BuildConfig.API_KEY
import com.zainalfn.core.data.source.remote.response.MovieDetailResponse
import com.zainalfn.core.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(
    private val apiService: ApiService
) {

    fun getMovies() : Flow<ApiResponse<ArrayList<MovieDetailResponse>>> =
        flow {
            try {
                val response = apiService.getMovies(API_KEY)
                val data = response.results

                if (data.isEmpty()){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(data))
                }
            } catch (e :Exception){
                emit(ApiResponse.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)
    
    fun getDetailMovies( movieId: String) : Flow<ApiResponse<MovieDetailResponse>> =
        flow {
            try {
                val response = apiService.getMovieDetail(movieId, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e :Exception){
                emit(ApiResponse.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)

    fun getTvShows() : Flow<ApiResponse<ArrayList<TvShowDetailResponse>>> =
        flow {
            try {
                val response = apiService.getTvShows(API_KEY)
                val data = response.results

                if (data.isEmpty()){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(data))
                }
            } catch (e :Exception){
                emit(ApiResponse.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)


    fun getDetailTvShow( tvShowId: String) : Flow<ApiResponse<TvShowDetailResponse>> =
        flow {
            try {
                val response = apiService.getTvShowDetail(tvShowId, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e :Exception){
                emit(ApiResponse.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)

}