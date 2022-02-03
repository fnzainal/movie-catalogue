package com.zainalfn.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.databinding.ActivityDetailBinding
import com.zainalfn.moviecatalogue.util.*
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_TVSHOW


class DetailActivity : AppCompatActivity() {

    private var catalogData: CatalogueDetailEntity? = null
    private var mMenu: Menu? = null
    private var id: Int = 0
    private var isFavorite: Boolean = false
    private var typeCatalogue: Int = TYPE_TVSHOW
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        intent.extras?.let {
            val id = it.getInt(ID)
            typeCatalogue = it.getInt(TYPE)
            this.id = id

            id.let { idCatalogue ->
                when (typeCatalogue) {
                    TYPE_MOVIE -> {
                        getDetailMovie(idCatalogue)
                    }
                    else -> {
                        getDetailTvShow(idCatalogue)
                    }
                }
            }

            initObserver(id)
        }
    }

    private fun initObserver(id: Int) {
        viewModel.getFavoriteDetail(id).observe(this){
            isFavorite = it != null
            setFavIconState()
        }
    }

    private fun setFavIconState() {
        val iconResId = if (!isFavorite) {
            R.drawable.ic_round_favorite_border_24
        } else {
            R.drawable.ic_round_favorite_24
        }
        val favIcon = mMenu?.findItem(R.id.action_favorite)
        favIcon?.icon = ContextCompat.getDrawable(this, iconResId)
    }

    private fun enableFavButton(state: Boolean) {
        mMenu?.findItem(R.id.action_favorite)?.isEnabled = state
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
        mMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                saveOrRemoveFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveOrRemoveFavorite() {
        if (isFavorite){
            viewModel.removeFromFavorite(id)
            isFavorite = false
        } else {
            catalogData?.apply {
                isFavorite = true
//                val data : CatalogueDetailEntity = prepareWithType(this)
//                Log.i(DetailActivity::class.simpleName,"save favorite//\n"+ Gson().toJson(data))
                if (typeCatalogue == TYPE_MOVIE) {
                    viewModel.addMovieToFavorite(this)
                } else {
                    viewModel.addTvShowToFavorite(this)
                }
            }
        }
        setFavIconState()
    }

    private fun prepareWithType(catalogueDetailEntity: CatalogueDetailEntity): CatalogueDetailEntity {
        catalogueDetailEntity.apply {
            return CatalogueDetailEntity(
                id, name, overview, genres, voteAverage, posterPath, releaseDate, type
            )
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

    private fun ActivityDetailBinding.renderToView(catalog: CatalogueDetailEntity) {
        catalog.apply {
            catalogData = this
            detailTitleTv.text = name
            detailGenreTv.text = genres
            if (releaseDate.isNullOrEmpty()) {
                detailYearTv.gone()
            } else {
                detailYearTv.visible()
                detailYearTv.text = toReadableDate(releaseDate)
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