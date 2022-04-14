package com.zainalfn.moviecatalogue.ui.discover.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zainalfn.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(
    private val useCase: CatalogueUseCase
) : ViewModel() {

    fun getTvShows() = useCase.getTvShows().asLiveData()
}