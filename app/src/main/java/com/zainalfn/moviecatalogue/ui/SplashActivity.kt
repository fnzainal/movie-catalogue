package com.zainalfn.moviecatalogue.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.zainalfn.moviecatalogue.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler(Looper.getMainLooper()).postDelayed({
            this.finish()
            startActivity(Intent(
                this,
                MainActivity::class.java))
        }, 2000)
    }
}