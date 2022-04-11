package com.zainalfn.moviecatalogue.ui.favorite

import androidx.lifecycle.ViewModel
import com.zainalfn.core.data.CatalogueRepository

class FavoriteViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getFavMovie() = repository.getFavMovies()
    fun getFavTvShow() = repository.getFavTvShows()
}