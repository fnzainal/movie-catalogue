package com.zainalfn.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zainalfn.core.domain.usecase.CatalogueUseCase

class FavoriteViewModel(
    private val useCase: CatalogueUseCase
) : ViewModel() {

    fun getFavMovie() = useCase.getFavMovies().asLiveData()
    fun getFavTvShow() = useCase.getFavTvShows().asLiveData()
}