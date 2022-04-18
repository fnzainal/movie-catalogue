package com.zainalfn.moviecatalogue.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.util.*
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private var catalogData: CatalogueDetail? = null
    private var favMenu: MenuItem? = null
    private var idArgs: Int = 0
    private var isFavorite: Boolean = false
    private var typeCatalogue: Int = TYPE_TVSHOW
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let {
            val id = it.getInt(ID)
            typeCatalogue = it.getInt(TYPE)
            idArgs = id
            initObserver()
        }
    }

    private fun initObserver() {
        viewModel.getFavoriteDetail(idArgs).observe(this){
            isFavorite = it?.id != 0

            if (isFavorite) {
                // if favorite, render data from local
                it?.apply {
                    binding?.renderToView(this)
                }
            } else {
                // if not favorite get detail from api
                when (typeCatalogue) {
                    TYPE_MOVIE -> {
                        getDetailMovie(idArgs)
                    }
                    else -> {
                        getDetailTvShow(idArgs)
                    }
                }
            }

            setFavIconState()
        }
    }

    private fun setFavIconState() {
        val iconResId = if (isFavorite) {
            R.drawable.ic_round_favorite_24
        } else {
            R.drawable.ic_round_favorite_border_24
        }
        Handler(Looper.getMainLooper()).postDelayed({
            favMenu?.icon = ContextCompat.getDrawable(this, iconResId)
        },200)
    }

    private fun enableFavButton(state: Boolean) {
        favMenu?.isEnabled = state
    }

    private fun getDetailTvShow(id: Int) {
        viewModel.getDetailTvShow(id).observe(this) {
            when(it.status){
                Status.LOADING -> showLoading(true)
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.apply {
                        binding?.renderToView(this)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        favMenu = menu.findItem(R.id.action_favorite)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home-> { finish() }
            R.id.action_favorite -> {
                saveOrRemoveFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveOrRemoveFavorite() {
        if (isFavorite){
            viewModel.removeFromFavorite(idArgs)
            isFavorite = false
        } else {
            catalogData?.apply {
                isFavorite = true
                if (typeCatalogue == TYPE_MOVIE) {
                    viewModel.addMovieToFavorite(this)
                } else {
                    viewModel.addTvShowToFavorite(this)
                }
            }
        }
    }

    private fun getDetailMovie(id: Int) {
        viewModel.getDetailMovie(id).observe(this) {
            when(it.status){
                Status.LOADING -> showLoading(true)
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.apply {
                        binding?.renderToView(this)
                    }
                }
            }
        }
    }

    private fun showLoading(isloading: Boolean) {
        binding?.apply {
            if (isloading) {
                enableFavButton(false)
                detailProgress.visible()
                detailContent.gone()
            } else {
                enableFavButton(true)
                detailProgress.gone()
                detailContent.visible()

            }
        }
    }

    private fun ActivityDetailBinding.renderToView(catalog: CatalogueDetail) {
        catalog.apply {
            catalogData = this
            detailTitleTv.text = name
            detailGenreTv.text = genres
            if (releaseDate.isNullOrEmpty()) {
                detailYearTv.gone()
            } else {
                detailYearTv.visible()
                detailYearTv.text = toReadableDate(releaseDate.toString())
            }
            detailOverviewTv.text = overview
            "$voteAverage%".also { detailScoreTv.text = it }
            detailThumbnailIv.loadImage(posterPath)
            detailThumbnailIv.tag = posterPath
        }
    }

    companion object {
        const val ID = "id_catalogue"
        const val TYPE = "type_catalogue"
    }
}