package com.zainalfn.moviecatalogue.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.elevation = 0f
        initNavigation()
    }

    private fun initNavigation() {
        binding?.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
            NavigationUI.setupWithNavController(
                bottomNavMain,
                navHostFragment.navController
            )
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

}