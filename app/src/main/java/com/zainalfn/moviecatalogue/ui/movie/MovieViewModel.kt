package com.zainalfn.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueData
import com.zainalfn.moviecatalogue.data.DummyData

class MovieViewModel : ViewModel() {

    fun getMovies(): ArrayList<CatalogueData> = DummyData.getMovie()
    fun getDetailMovie(id : Int): CatalogueData = DummyData.getDetailMovie(id)
}