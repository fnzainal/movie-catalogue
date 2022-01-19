package com.zainalfn.moviecatalogue.data.source.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by zainal on 1/4/22 - 7:45 AM
 */
@Parcelize
data class CatalogueData(
    val id: Int,
    val title: String,
    val overview: String,
    val genre: String,
    val score: String,
    val poster: Int,
    val year: Int?=0,
) : Parcelable