package com.zainalfn.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.domain.usecase.CatalogueUseCase
import kotlinx.coroutines.launch
class DetailViewModel(
    private val repository: CatalogueUseCase
) : ViewModel() {

    fun getDetailMovie(id: Int) = repository.getDetailMovie(id.toString()).asLiveData()
    fun getDetailTvShow(id: Int) = repository.getDetailTvShow(id.toString()).asLiveData()

    fun addMovieToFavorite(data: CatalogueDetail) {
        viewModelScope.launch {
            repository.addMovieToFavorite(data)
        }
    }
    fun addTvShowToFavorite(data: CatalogueDetail) {
        viewModelScope.launch {
            repository.addTvShowToFavorite(data)
        }
    }
    fun removeFromFavorite(id: Int) = viewModelScope.launch { repository.removeFromFavorite(id) }

    fun getFavoriteDetail(id: Int) = repository.getDetailFavorite(id).asLiveData()
}

