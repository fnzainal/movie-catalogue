package com.zainalfn.core.domain.repository

import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by zainal on 1/17/22 - 7:55 AM
 */
interface ICatalogueRepository {
    fun getMovies(): Flow<Resource<List<Catalogue>>>
    fun getDetailMovie(movieId: String): Flow<Resource<CatalogueDetail>>

    fun getTvShows(): Flow<Resource<List<Catalogue>>>
    fun getDetailTvShow(tvShowId: String): Flow<Resource<CatalogueDetail>>

    fun getFavMovies(): Flow<List<CatalogueDetail>?>
    fun getFavTvShows(): Flow<List<CatalogueDetail>?>
    fun getDetailFavorite(id: Int): Flow<CatalogueDetail?>

    suspend fun addMovieToFavorite(entity: CatalogueDetail)
    suspend fun addTvShowToFavorite(entity: CatalogueDetail)
    suspend fun removeFromFavorite(id: Int)
}