package com.zainalfn.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity

/**
 * Created by zainal on 1/17/22 - 7:55 AM
 */
interface CatalogueDataSource {
    fun getMovies(): LiveData<ArrayList<CatalogueEntity>>
    fun getDetailMovie(movieId: String): LiveData<CatalogueDetailEntity>
    fun getTvShows(): LiveData<ArrayList<CatalogueEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<CatalogueDetailEntity>
}