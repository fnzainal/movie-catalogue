package com.zainalfn.core.data.source.local

import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mCatalogueDao: CatalogueDao) {

    fun getFavMovies(): Flow<List<CatalogueDetailEntity>?> = mCatalogueDao.getFavoriteMovies()
    fun getFavTvShows(): Flow<List<CatalogueDetailEntity>?> = mCatalogueDao.getFavoriteTvShows()
    fun getFavoriteById(id: Int): Flow<CatalogueDetailEntity> = mCatalogueDao.getFavoriteById(id)

    suspend fun setMovieFavorite(entity: CatalogueDetailEntity) {
        entity.apply {
            type = TYPE_MOVIE
            mCatalogueDao.insertFavorite(this)
        }
    }

    suspend fun setTvShowFavorite(entity: CatalogueDetailEntity) {
        entity.apply {
            type = TYPE_TVSHOW
            mCatalogueDao.insertFavorite(this)
        }
    }

    suspend fun removeFavorite(idData: Int) {
        mCatalogueDao.removeFavorite(idData)
    }
}