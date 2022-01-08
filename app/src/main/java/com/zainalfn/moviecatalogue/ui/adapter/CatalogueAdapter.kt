package com.zainalfn.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainalfn.moviecatalogue.data.CatalogueData
import com.zainalfn.moviecatalogue.databinding.ItemCatalogueMovieBinding

class CatalogueAdapter(
    private var listCatalogue: ArrayList<CatalogueData>,
    private val onClick: (CatalogueData: CatalogueData) -> Unit
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
        fun bind(data: CatalogueData) {
            with(binding) {
                data.apply {
                    itemMovieTitleTv.text = title
                    itemMovieGenreTv.text = genre
                    itemMovieYearTv.text = year.toString()
                    "$score%".also { itemMovieScoreTv.text = it }
                    poster.let { itemMovieThumbnailIv.setImageResource(it) }
                    root.setOnClickListener { onClick(this) }
                }
            }
        }
    }
}