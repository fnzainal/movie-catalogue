package com.zainalfn.moviecatalogue.ui.discover.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.core.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.core.domain.model.Catalogue
import com.zainalfn.core.ui.CatalogueAdapter
import com.zainalfn.core.util.Status
import com.zainalfn.core.util.gone
import com.zainalfn.core.util.visible
import com.zainalfn.moviecatalogue.databinding.FragmentListTvShowBinding
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private var catalogueAdapter: CatalogueAdapter? = null
    private val viewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentListTvShowBinding? = null
    private val binding get() = _binding

    @Suppress("UnnecessaryVariable")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListTvShowBinding.inflate(inflater, container, false)
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

    private fun FragmentListTvShowBinding.initObserver() {
        viewModel.getTvShows().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        requireActivity(), "Failed get Tv shows.\nE:" + it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.apply {
                        if (this.isNotEmpty()) {
                            tvshowEmptyTv.gone()
                            renderToView(this)
                        } else {
                            tvshowEmptyTv.visible()
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
            tvshowListRv.adapter = catalogueAdapter
        }
    }

    private fun FragmentListTvShowBinding.setupAdapterRV() {
        tvshowListRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun FragmentListTvShowBinding.showLoading(loading: Boolean) {
        if (loading) {
            tvshowProgress.visible()
        } else {
            tvshowProgress.gone()
        }
    }

    private fun onClickCatalogue(it: Catalogue) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, it.id)
        intent.putExtra(DetailActivity.TYPE, TYPE_TVSHOW)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}