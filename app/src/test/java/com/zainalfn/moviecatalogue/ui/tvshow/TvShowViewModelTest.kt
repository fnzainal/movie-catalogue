package com.zainalfn.moviecatalogue.ui.tvshow

import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.data.source.local.CatalogueData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by zainal on 1/8/22 - 9:16 AM
 */
class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel
    private lateinit var movie: CatalogueData


    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
        movie = CatalogueData(
            0,
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a " +
                    "strange boy whom he named Goku. Gohan raised him and trained Goku in martial " +
                    "arts until he died. The young and very strong boy was on his own, but easily" +
                    " managed. Then one day, Goku met a teenage girl named Bulma, whose search for" +
                    " the mystical Dragon Balls brought her to Gokus home. Together, they set off" +
                    " to find all seven and to grant her wish.",
            "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
            "80",
            R.drawable.poster_dragon_ball,
            1986
        )
    }

    @Test
    fun getMovies() {
        val tvEntities = viewModel.getTvShows()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(10, tvEntities.size)
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = viewModel.getDetailTvShows(0)
        Assert.assertEquals(movie.id, detailMovie.id)
        Assert.assertEquals(movie.title, detailMovie.title)
        Assert.assertEquals(movie.overview, detailMovie.overview)
        Assert.assertEquals(movie.genre, detailMovie.genre)
        Assert.assertEquals(movie.score, detailMovie.score)
        Assert.assertEquals(movie.year, detailMovie.year)
        Assert.assertEquals(movie.poster, detailMovie.poster)
    }
}