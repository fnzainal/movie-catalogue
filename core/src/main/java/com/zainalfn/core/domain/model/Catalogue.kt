package com.zainalfn.core.domain.model

data class Catalogue(
    val id: Int?,
    val name: String?,
    val voteAverage: Double?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String? = null,
    var type: Int = 0
)
