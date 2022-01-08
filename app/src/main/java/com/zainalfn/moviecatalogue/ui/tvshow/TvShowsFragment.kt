package com.zainalfn.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.moviecatalogue.data.CatalogueData
import com.zainalfn.moviecatalogue.databinding.FragmentListTvShowBinding
import com.zainalfn.moviecatalogue.ui.adapter.CatalogueAdapter
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity

class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private var _binding: FragmentListTvShowBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTvShowBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[TvShowViewModel::class.java]

        binding.apply {
            tvshowListRv.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                adapter = CatalogueAdapter(viewModel.getTvShows()){
                    onClickCatalogue(it)
                }
            }
        }
    }

    private fun onClickCatalogue(it: CatalogueData) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, it.id)
        intent.putExtra(DetailActivity.TYPE, DetailActivity.TV_SHOW)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}