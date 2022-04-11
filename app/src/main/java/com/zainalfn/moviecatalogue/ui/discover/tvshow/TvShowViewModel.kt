package com.zainalfn.moviecatalogue.ui.discover.tvshow

import androidx.lifecycle.ViewModel
import com.zainalfn.core.data.CatalogueRepository

class TvShowViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getTvShows() = repository.getTvShows()
}