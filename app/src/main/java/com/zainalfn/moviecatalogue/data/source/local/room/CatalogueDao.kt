package com.zainalfn.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_TVSHOW

@Dao
interface CatalogueDao {
    @Query("SELECT * FROM catalog_entities WHERE type = $TYPE_MOVIE")
    fun getMovies(): DataSource.Factory<Int, CatalogueEntity>

    @Query("SELECT * FROM catalog_entities WHERE type = $TYPE_TVSHOW")
    fun getTvShows(): DataSource.Factory<Int, CatalogueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatalogue(movies: List<CatalogueEntity>)

    @Query("SELECT * FROM catalog_entities WHERE id = :id")
    fun getCatalogueById(id: Int): LiveData<CatalogueDetailEntity?>

    @Query("SELECT * FROM favorite_entities WHERE id = :id")
    fun getFavoriteById(id: Int): LiveData<CatalogueDetailEntity?>

    @Query("SELECT * FROM favorite_entities WHERE type = $TYPE_MOVIE")
    fun getFavoriteMovies(): DataSource.Factory<Int, CatalogueDetailEntity>

    @Query("SELECT * FROM favorite_entities WHERE type = $TYPE_TVSHOW")
    fun getFavoriteTvShows(): DataSource.Factory<Int, CatalogueDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movies: CatalogueDetailEntity)

    @Query("DELETE FROM favorite_entities WHERE id = :id")
    fun removeFavorite(id: Int)
}
