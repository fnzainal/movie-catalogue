package com.zainalfn.moviecatalogue.ui.discover.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.zainalfn.core.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.util.Resource
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
    private lateinit var catalogueRepository: com.zainalfn.core.data.CatalogueRepository

    @Mock
    private lateinit var pagedList: PagedList<CatalogueEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<CatalogueEntity>>>

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<CatalogueEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = tvShowViewModel.getTvShows().value?.data
        verify(catalogueRepository).getTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(3, tvShow?.size)

        tvShowViewModel.getTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}