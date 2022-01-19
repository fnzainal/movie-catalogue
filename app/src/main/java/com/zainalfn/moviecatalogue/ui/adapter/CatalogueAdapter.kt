package com.zainalfn.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.databinding.ItemCatalogueMovieBinding
import com.zainalfn.moviecatalogue.util.loadImage
import com.zainalfn.moviecatalogue.util.toReadableDate

class CatalogueAdapter(
    private var listCatalogue: ArrayList<CatalogueEntity>,
    private val onClick: (CatalogueEntity: CatalogueEntity) -> Unit
) : RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemCatalogueBinding =
            ItemCatalogueMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatalogueViewHolder(itemCatalogueBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        holder.bind(listCatalogue[position])
    }

    override fun getItemCount(): Int = listCatalogue.size

    inner class CatalogueViewHolder(private val binding: ItemCatalogueMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CatalogueEntity) {
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
}