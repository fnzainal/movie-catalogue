package com.zainalfn.moviecatalogue.ui.discover.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.ui.CatalogueAdapter
import com.zainalfn.core.util.Status
import com.zainalfn.core.util.gone
import com.zainalfn.core.util.visible
import com.zainalfn.moviecatalogue.databinding.FragmentListCatalogueBinding
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private var catalogueAdapter: CatalogueAdapter? = null
    private val viewModel: MovieViewModel by viewModel()
    private var _binding: FragmentListCatalogueBinding? = null


    private val binding get() = _binding

    @Suppress("UnnecessaryVariable")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCatalogueBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            setupAdapterRV()
            initObserver()
        }
    }

    private fun FragmentListCatalogueBinding.initObserver() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        requireActivity(), "Failed get movies.\nE:" + it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.apply {
                        if (this.isNotEmpty()) {
                            renderToView(this)
                            movieEmptyTv.gone()
                        } else {
                            movieEmptyTv.visible()
                        }
                    }
                }
            }
        }
    }

    private fun renderToView(list: List<Catalogue>) {
        catalogueAdapter = CatalogueAdapter(list) { data ->
            onClickCatalogue(data)
        }
        binding?.apply {
            movieListRv.adapter = catalogueAdapter
        }
    }

    private fun FragmentListCatalogueBinding.setupAdapterRV() {
        movieListRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                movieProgress.visible()
            } else {
                movieProgress.gone()
            }
        }
    }

    private fun onClickCatalogue(it: Catalogue) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, it.id)
        intent.putExtra(DetailActivity.TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}