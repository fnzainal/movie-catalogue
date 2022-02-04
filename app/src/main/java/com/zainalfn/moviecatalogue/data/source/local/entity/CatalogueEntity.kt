package com.zainalfn.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog_entities")
data class CatalogueEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,
    @ColumnInfo(name = "posterPath")
    val posterPath: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = null,
    @ColumnInfo(name = "type")
    var type: Int = 0
)
