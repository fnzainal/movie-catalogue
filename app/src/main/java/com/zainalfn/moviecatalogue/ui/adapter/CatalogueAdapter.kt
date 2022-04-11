package com.zainalfn.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainalfn.moviecatalogue.R
import com.zainalfn.core.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.databinding.ItemCatalogueMovieBinding
import com.zainalfn.core.util.loadImage
import com.zainalfn.core.util.toReadableDate

class CatalogueAdapter(
    private val onClick: (CatalogueEntity: CatalogueEntity) -> Unit
) : PagedListAdapter<CatalogueEntity, CatalogueAdapter.CatalogueViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemCatalogueBinding =
            ItemCatalogueMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return CatalogueViewHolder(itemCatalogueBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

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




    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogueEntity>() {
            override fun areItemsTheSame(oldItem: CatalogueEntity,
                                         newItem: CatalogueEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatalogueEntity,
                                            newItem: CatalogueEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}