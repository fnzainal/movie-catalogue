package com.zainalfn.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.util.DummyData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by zainal on 1/21/22 - 7:40 AM
 */
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<CatalogueDetailEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<CatalogueDetailEntity>

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovieDetail = DummyData.getDetailMovie().first()
        val dummyMovieDetailId = dummyMovieDetail.id.toString()
        val movieDetail = MutableLiveData<CatalogueDetailEntity>()
        movieDetail.value = dummyMovieDetail

        Mockito.`when`(catalogueRepository.getDetailMovie(dummyMovieDetailId))
            .thenReturn(movieDetail)
        val movieDetailEntity =
            detailViewModel.getDetailMovie(dummyMovieDetailId.toInt()).value as CatalogueDetailEntity
        verify(catalogueRepository).getDetailMovie(dummyMovieDetailId)

        Assert.assertNotNull(movieDetailEntity)
        assertEquals(dummyMovieDetail.id, movieDetailEntity.id)
        assertEquals(dummyMovieDetail.name, movieDetailEntity.name)
        assertEquals(dummyMovieDetail.posterPath, movieDetailEntity.posterPath)
        assertEquals(dummyMovieDetail.overview, movieDetailEntity.overview)
        assertEquals(dummyMovieDetail.genres, movieDetailEntity.genres)
        assertEquals(dummyMovieDetail.voteAverage, movieDetailEntity.voteAverage)

        detailViewModel.getDetailMovie(dummyMovieDetailId.toInt()).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovieDetail)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShowDetail = DummyData.getDetailTvShow().first()
        val dummyTvShowDetailId = dummyTvShowDetail.id.toString()
        val tvShowDetail = MutableLiveData<CatalogueDetailEntity>()
        tvShowDetail.value = dummyTvShowDetail

        Mockito.`when`(catalogueRepository.getDetailTvShow(dummyTvShowDetailId))
            .thenReturn(tvShowDetail)
        val tvShowDetailEntity =
            detailViewModel.getDetailTvShow(dummyTvShowDetailId.toInt()).value as CatalogueDetailEntity
        verify(catalogueRepository).getDetailTvShow(dummyTvShowDetailId)

        Assert.assertNotNull(tvShowDetailEntity)
        assertEquals(dummyTvShowDetail.id, tvShowDetailEntity.id)
        assertEquals(dummyTvShowDetail.name, tvShowDetailEntity.name)
        assertEquals(dummyTvShowDetail.posterPath, tvShowDetailEntity.posterPath)
        assertEquals(dummyTvShowDetail.overview, tvShowDetailEntity.overview)
        assertEquals(dummyTvShowDetail.genres, tvShowDetailEntity.genres)
        assertEquals(dummyTvShowDetail.voteAverage, tvShowDetailEntity.voteAverage)

        detailViewModel.getDetailTvShow(dummyTvShowDetailId.toInt()).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShowDetail)
    }

}