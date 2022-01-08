package com.zainalfn.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.moviecatalogue.databinding.FragmentListCatalogueBinding
import com.zainalfn.moviecatalogue.ui.adapter.CatalogueAdapter


class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private var _binding: FragmentListCatalogueBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCatalogueBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[TvShowViewModel::class.java]

        binding.apply {
            movieListRv.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                adapter = CatalogueAdapter(viewModel.getTvShows()){
                    println(it.title)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}