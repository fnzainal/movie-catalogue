package com.zainalfn.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.util.DummyData
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by zainal on 1/8/22 - 9:16 AM
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var tvShowObserver: Observer<ArrayList<CatalogueEntity>>

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = DummyData.getTvShow()
        val tvShows = MutableLiveData<ArrayList<CatalogueEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = tvShowViewModel.getTvShows().value
        verify(catalogueRepository).getTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(2, tvShow?.size)

        tvShowViewModel.getTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}