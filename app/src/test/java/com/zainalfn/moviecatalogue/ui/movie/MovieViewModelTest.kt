package com.zainalfn.moviecatalogue.ui.movie

import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.data.CatalogueData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by zainal on 1/8/22 - 9:12 AM
 */
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private lateinit var movie: CatalogueData

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
        movie = CatalogueData(
            0,
            "Alita: Battle Angle",
            "When Alita awakens with no memory of who she is in a future world she does " +
                    "not recognize, she is taken in by Ido, a compassionate doctor who realizes " +
                    "that somewhere in this abandoned cyborg shell is the heart and soul of a " +
                    "young woman with an extraordinary past.",
            "Action, Science Fiction, Adventure",
            "71",
            R.drawable.poster_alita,
            2019
        )
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()
        Assert.assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = viewModel.getDetailMovie(0)
        assertEquals(movie.id, detailMovie.id)
        assertEquals(movie.title, detailMovie.title)
        assertEquals(movie.overview, detailMovie.overview)
        assertEquals(movie.genre, detailMovie.genre)
        assertEquals(movie.score, detailMovie.score)
        assertEquals(movie.year, detailMovie.year)
        assertEquals(movie.poster, detailMovie.poster)
    }
}