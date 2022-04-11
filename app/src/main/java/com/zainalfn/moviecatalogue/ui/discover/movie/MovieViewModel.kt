package com.zainalfn.moviecatalogue.ui.discover.movie

import androidx.lifecycle.ViewModel
import com.zainalfn.core.data.CatalogueRepository

class MovieViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getMovies() = repository.getMovies()
}