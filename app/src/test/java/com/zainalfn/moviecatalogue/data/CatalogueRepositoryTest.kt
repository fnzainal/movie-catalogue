package com.zainalfn.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.zainalfn.moviecatalogue.data.source.remote.RemoteDataSource
import com.zainalfn.moviecatalogue.util.DummyData
import com.zainalfn.moviecatalogue.util.LiveDataTestUtil
import com.zainalfn.moviecatalogue.util.toGenreString
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


/**
 * Created by zainal on 1/20/22 - 7:54 AM
 */
class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)
    private val moviesResponse = DummyData.getRemoteMovies()
    private val movieId = moviesResponse.first().id.toString()
    private val movieDetail = DummyData.getRemoteDetailMovie().first()
    private val tvShowsResponse = DummyData.getRemoteTvShow()
    private val tvShowId = tvShowsResponse.first().id.toString()
    private val tvShowDetail = DummyData.getRemoteDetailTvShow().first()

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getMovies())
        verify(remote).getMovies(any())
        Assert.assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
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
        assertEquals(movieDetail.id, movieDetailEntity.id)
        assertEquals(movieDetail.title, movieDetailEntity.name)
        assertEquals(movieDetail.overview, movieDetailEntity.overview)
        assertEquals(movieDetail.genres.toGenreString(), movieDetailEntity.genres)
        assertEquals(movieDetail.posterPath, movieDetailEntity.posterPath)
        assertEquals(movieDetail.voteAverage, movieDetailEntity.voteAverage)
        assertEquals(movieDetail.releaseDate, movieDetailEntity.releaseDate)
    }

    @Test
    fun getTvShows() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(
                tvShowsResponse
            )
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShowsResponse.size, tvShowEntities.size)
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
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
        assertEquals(tvShowDetail.name, tvShowDetailEntity.name)
        assertEquals(tvShowDetail.overview, tvShowDetailEntity.overview)
        assertEquals(tvShowDetail.genres.toGenreString(), tvShowDetailEntity.genres)
        assertEquals(tvShowDetail.posterPath, tvShowDetailEntity.posterPath)
        assertEquals(tvShowDetail.voteAverage, tvShowDetailEntity.voteAverage)
        assertEquals(tvShowDetail.first_air_date, tvShowDetailEntity.releaseDate)
    }
}