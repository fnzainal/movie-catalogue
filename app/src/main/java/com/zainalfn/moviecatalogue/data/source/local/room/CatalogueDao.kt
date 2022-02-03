package com.zainalfn.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity

@Dao
interface CatalogueDao {
    @RawQuery(observedEntities = [CatalogueDetailEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, CatalogueDetailEntity>

    @Query("SELECT * FROM entities_favorite WHERE id = :id LIMIT 1")
    fun getFavoriteById(id: Int): LiveData<CatalogueDetailEntity?>

    @Query("SELECT * FROM entities_favorite WHERE type = 0")
    fun getFavoriteMovies(): DataSource.Factory<Int, CatalogueDetailEntity>

    @RawQuery(observedEntities = [CatalogueDetailEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, CatalogueDetailEntity>

    @Query("SELECT * FROM entities_favorite WHERE type = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, CatalogueDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movies: CatalogueDetailEntity)

    @Query("DELETE FROM entities_favorite WHERE id = :id")
    fun removeFavorite(id: Int)
}
