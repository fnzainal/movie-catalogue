package com.zainalfn.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.databinding.FragmentListCatalogueBinding
import com.zainalfn.moviecatalogue.ui.adapter.CatalogueAdapter
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity
import com.zainalfn.moviecatalogue.util.ViewModelFactory
import com.zainalfn.moviecatalogue.util.gone
import com.zainalfn.moviecatalogue.util.visible


class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
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

        setupViewModel()

        binding.apply {
            movieListRv.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
            showLoading(true)
            viewModel.getMovies().observe(viewLifecycleOwner){
                showLoading(false)
                if (it.isNotEmpty()){
                    val adapter = CatalogueAdapter(it){
                        onClickCatalogue(it)
                    }
                    movieListRv.adapter = adapter
                    movieEmptyTv.gone()
                } else {
                    movieEmptyTv.visible()
                }
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading){
                movieProgress.visible()
            } else {
                movieProgress.gone()
            }
        }
    }

    private fun onClickCatalogue(it: CatalogueEntity) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, it.id)
        intent.putExtra(DetailActivity.TYPE, DetailActivity.MOVIE)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}