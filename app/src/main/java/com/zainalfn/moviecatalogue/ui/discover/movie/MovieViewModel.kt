package com.zainalfn.moviecatalogue.ui.discover.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zainalfn.core.domain.usecase.CatalogueUseCase

class MovieViewModel(
    private val useCase: CatalogueUseCase
) : ViewModel() {

    fun getMovies() = useCase.getMovies().asLiveData()
}