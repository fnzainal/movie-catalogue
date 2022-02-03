package com.zainalfn.moviecatalogue.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zainalfn.moviecatalogue.R

/**
 * Created by zainal on 1/3/22 - 7:44 AM
 */
class TabPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    companion object {
        val TITLE = intArrayOf(R.string.movies, R.string.tv_show)
    }

    private val listFragment = mutableListOf<Fragment>()
    override fun getItemCount(): Int = listFragment.size

    fun addFragment(fragment: Fragment){
        listFragment.add(fragment)
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}