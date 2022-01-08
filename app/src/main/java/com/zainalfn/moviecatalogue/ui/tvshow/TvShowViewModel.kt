package com.zainalfn.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.zainalfn.moviecatalogue.data.CatalogueData
import com.zainalfn.moviecatalogue.data.DummyData

class TvShowViewModel : ViewModel() {

    fun getTvShows(): ArrayList<CatalogueData> = DummyData.getTvShow()
    fun getDetailTvShows(id : Int): CatalogueData = DummyData.getDetailTvShow(id)
}