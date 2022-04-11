package com.zainalfn.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.zainalfn.core.data.CatalogueRepository
import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity

class DetailViewModel(
    private val repository: CatalogueRepository
) : ViewModel() {

    fun getDetailMovie(id: Int) = repository.getDetailMovie(id.toString())
    fun getDetailTvShow(id: Int) = repository.getDetailTvShow(id.toString())

    fun addMovieToFavorite(data: CatalogueDetailEntity) = repository.addMovieToFavorite(data)
    fun addTvShowToFavorite(data: CatalogueDetailEntity) = repository.addTvShowToFavorite(data)
    fun removeFromFavorite(id: Int) = repository.removeFromFavorite(id)

    fun getFavoriteDetail(id: Int) = repository.getDetailFavorite(id)
}