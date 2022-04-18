package com.zainalfn.moviecatalogue.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zainalfn.moviecatalogue.databinding.FragmentContainerBinding
import com.zainalfn.moviecatalogue.ui.adapter.TabPagerAdapter
import com.zainalfn.moviecatalogue.ui.adapter.TabPagerAdapter.Companion.TITLE
import com.zainalfn.moviecatalogue.ui.discover.movie.MovieFragment
import com.zainalfn.moviecatalogue.ui.discover.tvshow.TvShowsFragment

class DiscoverFragment : Fragment() {
    private var viewPager: ViewPager2? = null
    private var tabLayoutMediator: TabLayoutMediator? = null
    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding

    @Suppress("UnnecessaryVariable")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContainerBinding.inflate(layoutInflater, container, false)

        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        viewPager?.adapter = null
        _binding = null
    }

    private fun setupViewPager() {
        val tabPagerAdapter = TabPagerAdapter(
            childFragmentManager, viewLifecycleOwner.lifecycle
        )
        tabPagerAdapter.addFragment(MovieFragment())
        tabPagerAdapter.addFragment(TvShowsFragment())

        binding?.apply {
            viewPager = viewPagerContainer
            viewPager?.apply {
                adapter = tabPagerAdapter
                val tabs = tabsContainer
                tabLayoutMediator = TabLayoutMediator(tabs, this) { tab, position ->
                    tab.text = resources.getString(TITLE[position])
                }
                tabLayoutMediator?.attach()
            }

        }
    }


}