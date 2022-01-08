package com.zainalfn.moviecatalogue.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zainalfn.moviecatalogue.ui.movie.MovieFragment
import com.zainalfn.moviecatalogue.ui.tvshow.TvShowsFragment

/**
 * Created by zainal on 1/3/22 - 7:44 AM
 */
class TabAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MovieFragment()
            else -> TvShowsFragment()
        }
    }
}