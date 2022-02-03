package com.zainalfn.moviecatalogue.ui.discover.movie

import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity

class MovieViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getMovies() = repository.getMovies()
}