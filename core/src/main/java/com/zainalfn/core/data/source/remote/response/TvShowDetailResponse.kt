package com.zainalfn.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    val id: Int,
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String? = null,
    val genres: ArrayList<Genres>? = null,
    val first_air_date: String? = null,
)
