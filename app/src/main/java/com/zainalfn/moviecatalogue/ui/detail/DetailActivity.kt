package com.zainalfn.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zainalfn.moviecatalogue.data.CatalogueData
import com.zainalfn.moviecatalogue.databinding.ActivityDetailBinding
import com.zainalfn.moviecatalogue.ui.movie.MovieViewModel
import com.zainalfn.moviecatalogue.ui.tvshow.TvShowViewModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.extras?.let {
            val id = it.getInt(ID)

            val catalog = when(it.getInt(TYPE)){
                MOVIE->{
                    val viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
                    viewModel.getDetailMovie(id)
                }
                else ->{
                    val viewModel = ViewModelProvider(this)[TvShowViewModel::class.java]
                    viewModel.getDetailTvShows(id)
                }
            }

            binding.renderToView(catalog)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ActivityDetailBinding.renderToView(catalog: CatalogueData) {
        catalog.apply {
            detailThumbnailIv.setImageResource(poster)
            detailTitleTv.text = title
            detailGenreTv.text = genre
            detailYearTv.text = year.toString()
            detailOverviewTv.text = overview
            "$score%".also { detailScoreTv.text = it }
        }
    }

    companion object{
        val ID = "id_catalogue"
        val TYPE = "type_catalogue"

        val MOVIE = 0
        val TV_SHOW = 1

    }
}