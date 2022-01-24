package com.zainalfn.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueRepository

class DetailViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getDetailMovie(id: Int) = repository.getDetailMovie(id.toString())
    fun getDetailTvShow(id: Int) = repository.getDetailTvShow(id.toString())
}