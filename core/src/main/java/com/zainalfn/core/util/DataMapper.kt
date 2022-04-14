package com.zainalfn.core.util

import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.data.source.remote.response.MovieDetailResponse
import com.zainalfn.core.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.domain.model.CatalogueDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


/**
 * Created by zainal on 4/13/22 - 7:47 AM
 */
object DataMapper {

    fun detailListToDomain(input: List<CatalogueDetailEntity>?): List<CatalogueDetail>? {
        return input?.map {
            detailToDomain(it)
        }
    }

    fun detailToDomain(it: CatalogueDetailEntity?): CatalogueDetail {
        it?.let {
            return CatalogueDetail(
                it.id,
                it.name,
                it.overview,
                it.genres,
                it.voteAverage,
                it.posterPath,
                it.releaseDate,
                it.type
            )
        }

        return CatalogueDetail()
    }

    fun catalogueResponseToDomain(input: List<MovieDetailResponse>): Flow<ArrayList<Catalogue>> {
        val list = ArrayList<Catalogue>()
        input.map {
            list.add(
                Catalogue(
                    it.id,
                    it.title,
                    it.voteAverage,
                    it.posterPath,
                    it.overview,
                    it.releaseDate
                )
            )
        }
        return flowOf(list)
    }

    fun catalogueResponseToDomainTv(input: List<TvShowDetailResponse>): Flow<ArrayList<Catalogue>> {
        val list = ArrayList<Catalogue>()
        input.map {
            list.add(
                Catalogue(
                    it.id,
                    it.name,
                    it.voteAverage,
                    it.posterPath,
                    it.overview,
                    it.first_air_date
                )
            )
        }
        return flowOf(list)
    }

    fun mapDetailToEntity(it: CatalogueDetail) =
        CatalogueDetailEntity(
            it.id,
            it.name,
            it.overview,
            it.genres,
            it.voteAverage,
            it.posterPath,
            it.releaseDate,
            it.type
        )

    fun responseDetailToDomainMovie(it: MovieDetailResponse): Flow<CatalogueDetail> =
        flowOf(
            CatalogueDetail(
                it.id,
                it.title,
                it.overview,
                it.genres.toGenreString(),
                it.voteAverage,
                it.posterPath,
                it.releaseDate,
                TYPE_MOVIE
            )
        )

    fun responseDetailToDomainTv(it: TvShowDetailResponse): Flow<CatalogueDetail> =
        flowOf(
            CatalogueDetail(
                it.id,
                it.name,
                it.overview,
                it.genres.toGenreString(),
                it.voteAverage,
                it.posterPath,
                it.first_air_date,
                TYPE_MOVIE
            )
        )
}
