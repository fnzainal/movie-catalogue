package com.zainalfn.favorite.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainalfn.core.databinding.ItemCatalogueMovieBinding
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.util.loadImage
import com.zainalfn.core.util.toReadableDate
import com.zainalfn.moviecatalogue.R

class CatalogueFavoriteAdapter(
    private val list: List<CatalogueDetail>,
    private val onClick: (catalogue: CatalogueDetail) -> Unit
) : RecyclerView.Adapter<CatalogueFavoriteAdapter.CatalogueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemCatalogueBinding =
            ItemCatalogueMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatalogueViewHolder(itemCatalogueBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        list[position].let { holder.bind(it) }
    }

    inner class CatalogueViewHolder(private val binding: ItemCatalogueMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CatalogueDetail) {
            with(binding) {
                data.apply {
                    itemMovieTitleTv.text = name
                    itemMovieGenreTv.text = overview
                    releaseDate?.let {
                        itemMovieYearTv.text = toReadableDate(it)
                    }
                    "$voteAverage%".also { itemMovieScoreTv.text = it }
                    posterPath?.let { itemMovieThumbnailIv.loadImage(it) }
                        ?: itemMovieThumbnailIv.setImageResource(
                            R.drawable.ic_baseline_image_placeholder
                        )
                    root.setOnClickListener { onClick(this) }
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}