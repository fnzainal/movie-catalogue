package com.zainalfn.moviecatalogue.ui.favorite

import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity

class FavoriteViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getFavMovie() = repository.getFavMovies()
    fun getFavTvShow() = repository.getFavTvShows()

    fun addMovieToFavorite(data: CatalogueDetailEntity) = repository.addMovieToFavorite(data)
    fun addTvShowToFavorite(data: CatalogueDetailEntity) = repository.addTvShowToFavorite(data)

}