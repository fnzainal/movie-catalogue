package com.zainalfn.moviecatalogue.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.databinding.ActivityMainBinding
import com.zainalfn.moviecatalogue.util.TabAdapter

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = TabAdapter(this)
        binding.apply {
            mainViewPagerCatalogue.adapter = adapter
            TabLayoutMediator(mainTabsCatalogue, mainViewPagerCatalogue) { tab, position ->
                tab.text = getString(TITLE[position])
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                showAbout()
            }
            else -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
            .setTitle(R.string.action_about)
            .setMessage(R.string.about_text)
            .setPositiveButton(R.string.back) { d, _ -> d.dismiss() }
            .show()
    }

    companion object {
        private val TITLE = intArrayOf(R.string.movies, R.string.tv_show)
    }
}