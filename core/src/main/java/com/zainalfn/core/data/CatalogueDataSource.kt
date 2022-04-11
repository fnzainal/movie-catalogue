package com.zainalfn.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.core.data.source.local.entity.CatalogueEntity
import com.zainalfn.core.util.Resource

/**
 * Created by zainal on 1/17/22 - 7:55 AM
 */
interface CatalogueDataSource {
    fun getMovies(): LiveData<Resource<PagedList<CatalogueEntity>>>
    fun getDetailMovie(movieId: String): LiveData<Resource<CatalogueDetailEntity>>

    fun getTvShows(): LiveData<Resource<PagedList<CatalogueEntity>>>
    fun getDetailTvShow(tvShowId: String): LiveData<Resource<CatalogueDetailEntity>>

    fun getFavMovies(): LiveData<PagedList<CatalogueDetailEntity>>
    fun getFavTvShows(): LiveData<PagedList<CatalogueDetailEntity>>
    fun getDetailFavorite(id: Int): LiveData<CatalogueDetailEntity?>
    fun addMovieToFavorite(entity: CatalogueDetailEntity)
    fun addTvShowToFavorite(entity: CatalogueDetailEntity)
    fun removeFromFavorite(id: Int)
}