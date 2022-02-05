package com.zainalfn.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zainalfn.moviecatalogue.data.CatalogueRepository
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.util.DummyData
import com.zainalfn.moviecatalogue.util.Resource
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
class DetailViewModelTest {

    private val dummyMovie = DummyData.getDetailMovie().first()
    private val dummyMovieDetailId = dummyMovie.id.toString()

    private val dummyTvShowDetail = DummyData.getDetailTvShow().first()
    private val dummyTvShowDetailId = dummyTvShowDetail.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var catalogueObserver: Observer<Resource<CatalogueDetailEntity>>

    @Mock
    private lateinit var detailCatalogueObserver: Observer<CatalogueDetailEntity?>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<CatalogueDetailEntity>>

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovieDetail = Resource.success(DummyData.getDetailMovie().first())
        val movieDetail = MutableLiveData<Resource<CatalogueDetailEntity>>()
        movieDetail.value = dummyMovieDetail

        Mockito.`when`(catalogueRepository.getDetailMovie(dummyMovieDetailId))
            .thenReturn(movieDetail)
        val movieDetailEntity =
            detailViewModel.getDetailMovie(dummyMovieDetailId.toInt()).value as Resource<CatalogueDetailEntity>
        verify(catalogueRepository).getDetailMovie(dummyMovieDetailId)

        Assert.assertNotNull(movieDetailEntity)
        movieDetailEntity.data?.apply {
            assertEquals(dummyMovie.id, id)
            assertEquals(dummyMovie.name, name)
            assertEquals(dummyMovie.posterPath, posterPath)
            assertEquals(dummyMovie.overview, overview)
            assertEquals(dummyMovie.genres, genres)
            assertEquals(dummyMovie.voteAverage, voteAverage)
            assertEquals(dummyMovie.releaseDate, releaseDate)
        }

        detailViewModel.getDetailMovie(dummyMovieDetailId.toInt()).observeForever(catalogueObserver)
        verify(catalogueObserver).onChanged(dummyMovieDetail)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDataTvShow = Resource.success(dummyTvShowDetail)
        val tvShowDetail = MutableLiveData<Resource<CatalogueDetailEntity>>()
        tvShowDetail.value = dummyDataTvShow

        Mockito.`when`(catalogueRepository.getDetailTvShow(dummyTvShowDetailId))
            .thenReturn(tvShowDetail)
        val tvShowDetailEntity =
            detailViewModel.getDetailTvShow(dummyTvShowDetailId.toInt()).value as Resource<CatalogueDetailEntity>
        verify(catalogueRepository).getDetailTvShow(dummyTvShowDetailId)

        Assert.assertNotNull(tvShowDetailEntity)
        tvShowDetailEntity.data?.apply {
            assertEquals(dummyTvShowDetail.id, id)
            assertEquals(dummyTvShowDetail.name, name)
            assertEquals(dummyTvShowDetail.posterPath, posterPath)
            assertEquals(dummyTvShowDetail.overview, overview)
            assertEquals(dummyTvShowDetail.genres, genres)
            assertEquals(dummyTvShowDetail.voteAverage, voteAverage)
            assertEquals(dummyTvShowDetail.releaseDate, releaseDate)
        }

        detailViewModel.getDetailTvShow(dummyTvShowDetailId.toInt()).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDataTvShow)
    }


    @Test
    fun addMovieToFavorite() {
        val dummyDetailMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<CatalogueDetailEntity>>()
        movie.value = dummyDetailMovie

        dummyDetailMovie.data?.let { detailViewModel.addMovieToFavorite(it) }
        verify(catalogueRepository).addMovieToFavorite(movie.value!!.data as CatalogueDetailEntity)
    }

    @Test
    fun addTvShowToFavorite() {
        val dummyDetailTvShow = Resource.success(dummyTvShowDetail)
        val movie = MutableLiveData<Resource<CatalogueDetailEntity>>()
        movie.value = dummyDetailTvShow

        dummyDetailTvShow.data?.let { detailViewModel.addTvShowToFavorite(it) }
        verify(catalogueRepository).addTvShowToFavorite(movie.value!!.data as CatalogueDetailEntity)
    }

    @Test
    fun removeFromFavorite() {
        val dummyDetailTvShow = Resource.success(dummyTvShowDetail)
        val movie = MutableLiveData<Resource<CatalogueDetailEntity>>()
        movie.value = dummyDetailTvShow

        dummyDetailTvShow.data?.let { detailViewModel.removeFromFavorite(it.id) }
        movie.value?.data?.let { verify(catalogueRepository).removeFromFavorite(it.id)}
    }

    @Test
    fun getFavoriteDetail() {
        val movie = MutableLiveData<CatalogueDetailEntity>()
        movie.value = dummyMovie

        Mockito.`when`(catalogueRepository.getDetailFavorite(dummyMovieDetailId.toInt())).thenReturn(movie)
        val detailFavMovie = detailViewModel.getFavoriteDetail(dummyMovieDetailId.toInt()).value
        verify(catalogueRepository).getDetailFavorite(dummyMovieDetailId.toInt())

        Assert.assertNotNull(detailFavMovie)
        detailFavMovie?.apply {
            assertEquals(dummyMovie.id, id)
            assertEquals(dummyMovie.name, name)
            assertEquals(dummyMovie.posterPath, posterPath)
            assertEquals(dummyMovie.overview, overview)
            assertEquals(dummyMovie.genres, genres)
            assertEquals(dummyMovie.voteAverage, voteAverage)
            assertEquals(dummyMovie.releaseDate, releaseDate)
        }

        detailViewModel.getFavoriteDetail(dummyMovieDetailId.toInt()).observeForever(detailCatalogueObserver)
        verify(detailCatalogueObserver).onChanged(dummyMovie)
    }

}