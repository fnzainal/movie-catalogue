package com.zainalfn.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.data.source.local.entity.TYPE_TVSHOW
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM catalog_entities WHERE id = :id")
    fun getCatalogueById(id: Int): LiveData<CatalogueDetailEntity?>

    /* favorite group*/
    @Query("SELECT * FROM favorite_entities WHERE id = :id")
    fun getFavoriteById(id: Int): Flow<CatalogueDetailEntity>

    @Query("SELECT * FROM favorite_entities WHERE type = $TYPE_MOVIE")
    fun getFavoriteMovies(): Flow<List<CatalogueDetailEntity>?>

    @Query("SELECT * FROM favorite_entities WHERE type = $TYPE_TVSHOW")
    fun getFavoriteTvShows(): Flow<List<CatalogueDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movies: CatalogueDetailEntity)

    @Query("DELETE FROM favorite_entities WHERE id = :id")
    suspend fun removeFavorite(id: Int)
}
