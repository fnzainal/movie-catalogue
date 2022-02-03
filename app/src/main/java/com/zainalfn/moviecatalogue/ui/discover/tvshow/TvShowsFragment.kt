package com.zainalfn.moviecatalogue.ui.discover.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.moviecatalogue.databinding.FragmentListTvShowBinding
import com.zainalfn.moviecatalogue.ui.adapter.CatalogueAdapter
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity
import com.zainalfn.moviecatalogue.util.Status
import com.zainalfn.moviecatalogue.util.ViewModelFactory
import com.zainalfn.moviecatalogue.util.gone
import com.zainalfn.moviecatalogue.util.visible

class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private var _binding: FragmentListTvShowBinding? = null


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListTvShowBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        binding?.apply {
            tvshowListRv.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner) {
                when(it.status){
                    Status.LOADING-> showLoading(true)
                    Status.ERROR-> {
                        showLoading(false)
                        Toast.makeText(requireActivity(), it.message,
                            Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS-> {
                        showLoading(false)
                        it.data?.apply {
                            if (this.isNotEmpty()) {
                                val adapter = CatalogueAdapter(this) { data ->
                                    onClickCatalogue(data)
                                }
                                tvshowListRv.adapter = adapter
                            } else {
                                tvshowEmptyTv.visible()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun FragmentListTvShowBinding.showLoading(loading: Boolean) {
        if (loading) {
            tvshowProgress.visible()
        } else {
            tvshowProgress.gone()
        }
    }

    private fun onClickCatalogue(it: CatalogueEntity) {
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