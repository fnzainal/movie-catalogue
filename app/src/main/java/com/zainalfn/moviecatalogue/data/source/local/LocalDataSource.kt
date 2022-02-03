package com.zainalfn.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource(private val mCatalogueDao: CatalogueDao) {


    fun getFavMovies(): DataSource.Factory<Int, CatalogueDetailEntity> = mCatalogueDao.getFavoriteMovies()
    fun getFavTvShows(): DataSource.Factory<Int, CatalogueDetailEntity> = mCatalogueDao.getFavoriteTvShows()
    fun getFavoriteById(id: Int): LiveData<CatalogueDetailEntity?> = mCatalogueDao.getFavoriteById(id)

    fun setMovieFavorite(entity: CatalogueDetailEntity) {
        entity.apply {
            type = TYPE_MOVIE
            mCatalogueDao.insertFavorite(this)
        }
    }

    fun setTvShowFavorite(entity: CatalogueDetailEntity) {
        entity.apply {
            type = TYPE_TVSHOW
            mCatalogueDao.insertFavorite(this)
        }
    }

    fun removeFavorite(idData: Int) {
        mCatalogueDao.removeFavorite(idData)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }
}