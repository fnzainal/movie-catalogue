package com.zainalfn.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.zainalfn.moviecatalogue.data.source.local.LocalDataSource
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.data.source.remote.RemoteDataSource
import com.zainalfn.moviecatalogue.ui.utils.PagedListUtil
import com.zainalfn.moviecatalogue.util.*
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


/**
 * Created by zainal on 1/20/22 - 7:54 AM
 */
class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote,local, appExecutors)

    private val moviesResponse = DummyData.getRemoteMovies()
    private val movieId = moviesResponse.first().id.toString()
    private val movieDetail = DummyData.getRemoteDetailMovie().first()

    private val tvShowsResponse = DummyData.getRemoteTvShow()
    private val tvShowId = tvShowsResponse.first().id.toString()
    private val tvShowDetail = DummyData.getRemoteDetailTvShow().first()

    private val tvShowsFav = DummyData.getDetailTvShow()
    private val tvShowFavId = tvShowsFav.first().id.toString()
    private val tvShowShowDetail = tvShowsFav.first()

    private val moviesFav = DummyData.getDetailMovie()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogueEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getMovies()))
        verify(local).getAllMovies()
        Assert.assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieLoaded(
                movieDetail
            )
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))

        val movieDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        Assert.assertNotNull(movieDetailEntity)
        movieDetailEntity.data?.apply {
            assertEquals(movieDetail.id, id)
            assertEquals(movieDetail.title, name)
            assertEquals(movieDetail.overview, overview)
            assertEquals(movieDetail.genres.toGenreString(), genres)
            assertEquals(movieDetail.posterPath, posterPath)
            assertEquals(movieDetail.voteAverage, voteAverage)
            assertEquals(movieDetail.releaseDate, releaseDate)
        }
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CatalogueEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getTvShow()))
        verify(local).getAllTvShows()
        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShowsResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(
                tvShowDetail
            )
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        Assert.assertNotNull(tvShowDetailEntity)
        tvShowDetailEntity.data?.apply {
            assertEquals(tvShowDetail.id, id)
            assertEquals(tvShowDetail.name, name)
            assertEquals(tvShowDetail.overview, overview)
            assertEquals(tvShowDetail.genres.toGenreString(), genres)
            assertEquals(tvShowDetail.posterPath, posterPath)
            assertEquals(tvShowDetail.voteAverage, voteAverage)
            assertEquals(tvShowDetail.first_air_date, releaseDate)
        }
    }

}