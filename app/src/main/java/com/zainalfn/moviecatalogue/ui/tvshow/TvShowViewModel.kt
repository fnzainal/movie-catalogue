package com.zainalfn.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity

class TvShowViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getTvShows(): LiveData<ArrayList<CatalogueEntity>> = repository.getTvShows()
}