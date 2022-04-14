package com.zainalfn.core.domain.model

data class CatalogueDetail(
    val id: Int=0,
    val name: String? = null,
    val overview: String? = null,
    val genres: String? = null,
    val voteAverage: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    var type: Int = -1
)
