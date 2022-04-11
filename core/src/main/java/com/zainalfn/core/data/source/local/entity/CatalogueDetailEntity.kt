package com.zainalfn.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TYPE_MOVIE: Int = 0
const val TYPE_TVSHOW: Int = 1

@Entity(tableName = "favorite_entities")
data class CatalogueDetailEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "genres")
    val genres: String?,
    @ColumnInfo(name ="voteAverage")
    val voteAverage: Double?,
    @ColumnInfo(name ="posterPath")
    val posterPath: String?,
    @ColumnInfo(name ="releaseDate")
    val releaseDate: String? = null,
    @ColumnInfo(name ="type")
    var type: Int = -1
)
