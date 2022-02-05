package com.zainalfn.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by zainal on 2/4/22 - 9:21 AM
 */
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CatalogueDetailEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CatalogueDetailEntity>

    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogueRepository)

    }

    @Test
    fun getFavMovie() {
        val dummyMovies = pagedList
        Mockito.`when`(dummyMovies.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<CatalogueDetailEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(catalogueRepository.getFavMovies()).thenReturn(movies)
        val movie = viewModel.getFavMovie().value
        verify(catalogueRepository).getFavMovies()
        Assert.assertNotNull(movie)
        TestCase.assertEquals(3, movie?.size)

        viewModel.getFavMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getFavTvShow() {
        val dummyTvShows = pagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<CatalogueDetailEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(catalogueRepository.getFavTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShow().value
        verify(catalogueRepository).getFavTvShows()

        Assert.assertNotNull(tvShow)
        TestCase.assertEquals(3, tvShow?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}