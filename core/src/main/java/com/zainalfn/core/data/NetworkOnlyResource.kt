package com.zainalfn.core.data

import android.content.res.Resources
import com.zainalfn.core.R
import com.zainalfn.core.data.source.remote.ApiResponse
import com.zainalfn.core.util.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Resource.success(it)
                })
            }
            is ApiResponse.Error -> emit(Resource.error(apiResponse.errorMessage))
            is ApiResponse.Empty -> emit(
                Resource.error(
                    Resources.getSystem().getString(R.string.empty_data)
                )
            )
        }
    }


    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}