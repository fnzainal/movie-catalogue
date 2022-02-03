package com.zainalfn.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.util.Resource

/**
 * Created by zainal on 1/17/22 - 7:55 AM
 */
interface CatalogueDataSource {
//    fun getMovies(): LiveData<Resource<PagingData<CatalogueEntity>>>
//    fun getDetailMovie(movieId: String): LiveData<Resource<CatalogueDetailEntity>>
//    fun getTvShows(): LiveData<Resource<PagingData<CatalogueEntity>>>
//    fun getDetailTvShow(tvShowId: String): LiveData<Resource<CatalogueDetailEntity>>

    fun getMovies(): LiveData<ArrayList<CatalogueEntity>>
    fun getDetailMovie(movieId: String): LiveData<Resource<CatalogueDetailEntity>>
    fun getTvShows(): LiveData<ArrayList<CatalogueEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<Resource<CatalogueDetailEntity>>



    fun getFavMovies(): LiveData<PagedList<CatalogueDetailEntity>>
    fun getFavTvShows(): LiveData<PagedList<CatalogueDetailEntity>>
    fun getDetailFavorite(id: Int): LiveData<CatalogueDetailEntity?>
    fun addMovieToFavorite(entity: CatalogueDetailEntity)
    fun addTvShowToFavorite(entity: CatalogueDetailEntity)
    fun removeFromFavorite(id: Int)
}