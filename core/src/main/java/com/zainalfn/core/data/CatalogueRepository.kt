package com.zainalfn.core.data

import com.zainalfn.core.data.source.local.LocalDataSource
import com.zainalfn.core.data.source.remote.ApiResponse
import com.zainalfn.core.data.source.remote.RemoteDataSource
import com.zainalfn.core.data.source.remote.response.MovieDetailResponse
import com.zainalfn.core.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.domain.repository.ICatalogueRepository
import com.zainalfn.core.util.DataMapper
import com.zainalfn.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogueRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ICatalogueRepository {

    override fun getMovies(): Flow<Resource<List<Catalogue>>> {
        return object : NetworkOnlyResource<List<Catalogue>, ArrayList<MovieDetailResponse>>() {
            override fun loadFromNetwork(data: ArrayList<MovieDetailResponse>): Flow<List<Catalogue>> {
                return DataMapper.catalogueResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<MovieDetailResponse>>> {
                return remoteDataSource.getMovies()
            }
        }.asFlow()

    }

    override fun getDetailMovie(movieId: String): Flow<Resource<CatalogueDetail>> {
        return object : NetworkOnlyResource<CatalogueDetail, MovieDetailResponse>() {
            override fun loadFromNetwork(data: MovieDetailResponse): Flow<CatalogueDetail> {
                return DataMapper.responseDetailToDomainMovie(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovies(movieId)
            }
        }.asFlow()
    }

    override fun getTvShows(): Flow<Resource<List<Catalogue>>> {
        return object : NetworkOnlyResource<List<Catalogue>, ArrayList<TvShowDetailResponse>>() {
            override fun loadFromNetwork(data: ArrayList<TvShowDetailResponse>): Flow<List<Catalogue>> {
                return DataMapper.catalogueResponseToDomainTv(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<TvShowDetailResponse>>> {
                return remoteDataSource.getTvShows()
            }
        }.asFlow()
    }

    override fun getDetailTvShow(tvShowId: String): Flow<Resource<CatalogueDetail>> {
        return object : NetworkOnlyResource<CatalogueDetail, TvShowDetailResponse>() {
            override fun loadFromNetwork(data: TvShowDetailResponse): Flow<CatalogueDetail> {
                return DataMapper.responseDetailToDomainTv(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> {
                return remoteDataSource.getDetailTvShow(tvShowId)
            }
        }.asFlow()
    }

    override fun getFavMovies(): Flow<List<CatalogueDetail>?> {
        return localDataSource.getFavMovies().map{
            it?.let {
                DataMapper.detailListToDomain(it)
            }
        }
    }

    override fun getFavTvShows(): Flow<List<CatalogueDetail>?> {
        return localDataSource.getFavTvShows().map {
            it?.let {
                DataMapper.detailListToDomain(it)
            }
        }
    }

    override fun getDetailFavorite(id: Int): Flow<CatalogueDetail?> {
        return localDataSource.getFavoriteById(id).map {
            DataMapper.detailToDomain(it)
        }
    }

    override suspend fun addMovieToFavorite(entity: CatalogueDetail) {
        localDataSource.setMovieFavorite(
            DataMapper.mapDetailToEntity(entity)
        )
    }

    override suspend fun addTvShowToFavorite(entity: CatalogueDetail) {
        localDataSource.setTvShowFavorite(
            DataMapper.mapDetailToEntity(entity)
        )
    }

    override suspend fun removeFromFavorite(id: Int) {
        localDataSource.removeFavorite(id)
    }
}