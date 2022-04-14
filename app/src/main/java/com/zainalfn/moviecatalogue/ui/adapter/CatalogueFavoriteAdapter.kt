package com.zainalfn.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.util.loadImage
import com.zainalfn.core.util.toReadableDate
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.databinding.ItemCatalogueMovieBinding

class CatalogueFavoriteAdapter(
    private val onClick: (catalogue: CatalogueDetail) -> Unit
) : RecyclerView.Adapter<CatalogueFavoriteAdapter.CatalogueViewHolder>() {


    private val mData = mutableListOf<CatalogueDetail>()

    fun setData(data: List<CatalogueDetail>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemCatalogueBinding =
            ItemCatalogueMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatalogueViewHolder(itemCatalogueBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        mData[position].let { holder.bind(it) }
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

    override fun getItemCount(): Int = mData.size
}