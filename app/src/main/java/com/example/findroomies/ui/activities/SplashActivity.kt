package com.example.findroomies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R
import com.example.findroomies.ui.adapters.PagerAdapter

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val adapter = PagerAdapter(this)
        viewPager.adapter = adapter
    }
}