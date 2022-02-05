package com.zainalfn.moviecatalogue.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zainalfn.moviecatalogue.databinding.FragmentContainerBinding
import com.zainalfn.moviecatalogue.ui.adapter.TabPagerAdapter
import com.zainalfn.moviecatalogue.ui.adapter.TabPagerAdapter.Companion.TITLE
import com.zainalfn.moviecatalogue.ui.discover.movie.MovieFragment
import com.zainalfn.moviecatalogue.ui.discover.tvshow.TvShowsFragment

class DiscoverFragment : Fragment() {
    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContainerBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val tabPagerAdapter = TabPagerAdapter(requireActivity())
        tabPagerAdapter.addFragment(MovieFragment())
        tabPagerAdapter.addFragment(TvShowsFragment())

        binding?.apply {
            val viewPager = viewPagerContainer
            viewPager.adapter = tabPagerAdapter

            val tabs = tabsContainer
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TITLE[position])
            }.attach()

        }
    }


}