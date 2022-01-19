package com.zainalfn.moviecatalogue.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class CatalogueDetailEntity(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val genres: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val releaseDate: String? = null
)
