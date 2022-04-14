package com.zainalfn.core.domain.usecase

import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.domain.repository.ICatalogueRepository
import com.zainalfn.core.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by zainal on 4/14/22 - 8:38 AM
 */
class CatalogueInteractor(
    private val repository: ICatalogueRepository
) : CatalogueUseCase {

    override fun getMovies(): Flow<Resource<List<Catalogue>>> = repository.getMovies()

    override fun getDetailMovie(movieId: String): Flow<Resource<CatalogueDetail>> =
        repository.getDetailMovie(movieId)

    override fun getTvShows(): Flow<Resource<List<Catalogue>>> = repository.getTvShows()

    override fun getDetailTvShow(tvShowId: String): Flow<Resource<CatalogueDetail>> =
        repository.getDetailTvShow(tvShowId)

    override fun getFavMovies(): Flow<List<CatalogueDetail>?> = repository.getFavMovies()

    override fun getFavTvShows(): Flow<List<CatalogueDetail>?> = repository.getFavTvShows()

    override fun getDetailFavorite(id: Int): Flow<CatalogueDetail?> = repository.getDetailFavorite(id)

    override suspend fun addMovieToFavorite(entity: CatalogueDetail) = repository.addMovieToFavorite(entity)

    override suspend fun addTvShowToFavorite(entity: CatalogueDetail) = repository.addTvShowToFavorite(entity)

    override suspend fun removeFromFavorite(id: Int) = repository.removeFromFavorite(id)
}