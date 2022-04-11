package com.zainalfn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val id: Int,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val genres: ArrayList<Genres>? = null,
)
