package com.zainalfn.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.databinding.ActivityDetailBinding
import com.zainalfn.moviecatalogue.util.*

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        intent.extras?.let {
            val id = it.getInt(ID)
            val type = it.getInt(TYPE)

            id?.let { idCatalogue ->
                when (type) {
                    MOVIE -> {
                        getDetailMovie(idCatalogue)
                    }
                    else -> {
                        getDetailTvShow(idCatalogue)
                    }
                }
            }
        }
    }

    private fun getDetailTvShow(id: Int) {
        showLoading(true)
        viewModel.getDetailTvShow(id).observe(this) {
            it.apply {
                binding.renderToView(this)
                showLoading(false)

            }
        }
    }

    private fun getDetailMovie(id: Int) {
        showLoading(true)
        viewModel.getDetailMovie(id).observe(this) {
            it.apply {
                binding.renderToView(this)
                showLoading(false)
            }
        }
    }

    private fun showLoading(isloading: Boolean) {
        binding.apply {
            if (isloading){
                detailProgress.visible()
                detailContent.gone()
            } else {
                detailProgress.gone()
                detailContent.visible()

            }
        }
    }

    private fun ActivityDetailBinding.renderToView(catalog: CatalogueDetailEntity) {
        catalog.apply {
            detailThumbnailIv.loadImage(posterPath)
            detailTitleTv.text = name
            detailGenreTv.text = genres
            if (releaseDate.isNullOrEmpty()){
                detailYearTv.gone()
            } else {
                detailYearTv.visible()
                detailYearTv.text = toReadableDate(releaseDate)
            }
            detailOverviewTv.text = overview
            "$voteAverage%".also { detailScoreTv.text = it }
        }
    }

    companion object {
        const val ID = "id_catalogue"
        const val TYPE = "type_catalogue"

        const val MOVIE = 0
        const val TV_SHOW = 1
    }
}