package com.zainalfn.moviecatalogue.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class CatalogueEntity(
    val id: Int?,
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String? = null
)
