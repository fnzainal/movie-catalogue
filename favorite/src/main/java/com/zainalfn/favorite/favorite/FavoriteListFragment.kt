package com.zainalfn.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainalfn.core.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.core.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.core.domain.model.CatalogueDetail
import com.zainalfn.core.util.gone
import com.zainalfn.core.util.visible
import com.zainalfn.favorite.databinding.FragmentFavoriteListBinding
import com.zainalfn.favorite.di.favoriteModule
import com.zainalfn.moviecatalogue.ui.adapter.CatalogueFavoriteAdapter
import com.zainalfn.moviecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

private const val ARG_TYPE = "type_catalogue"

class FavoriteListFragment : Fragment() {

    private lateinit var liveData: LiveData<List<CatalogueDetail>?>
    private lateinit var favoriteAdapter: CatalogueFavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModel()
    private var typeArgs: Int = 0
    private var binding: FragmentFavoriteListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        arguments?.let {
            typeArgs = it.getInt(ARG_TYPE)
            initObserver(typeArgs)
        }
    }

    private fun initAdapter() {
        showLoading(true)
        favoriteAdapter = CatalogueFavoriteAdapter {
            onClickCatalogue(it)
        }
        binding?.apply {
            favoriteListRv.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = favoriteAdapter
            }
        }
    }

    private fun onClickCatalogue(it: CatalogueDetail) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, it.id)
        intent.putExtra(DetailActivity.TYPE, typeArgs)
        startActivity(intent)
    }

    private fun initObserver(typeArgs: Int) {
        liveData = when (typeArgs) {
            TYPE_MOVIE -> viewModel.getFavMovie()
            else -> viewModel.getFavTvShow()
        }

        liveData.observe(viewLifecycleOwner) { list ->
            binding?.apply {
                showLoading(false)
                if (list.isNullOrEmpty()) {
                    viewEmpty()
                } else {
                    favoriteEmptyTv.gone()
                    favoriteListRv.visible()
                    favoriteAdapter.setData(list)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (liveData != null) {
            initObserver(typeArgs)
        }
    }

    private fun FragmentFavoriteListBinding.viewEmpty() {
        favoriteListRv.gone()
        favoriteEmptyTv.visible()
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                favoriteProgress.visible()
            } else {
                favoriteProgress.gone()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding?.root
    }


    override fun onStop() {
        super.onStop()
        unloadKoinModules(favoriteModule)
    }

    companion object {
        fun newInstanceMovie() =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TYPE, TYPE_MOVIE)
                }
            }

        fun newInstanceTvShow() =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TYPE, TYPE_TVSHOW)
                }
            }
    }
}