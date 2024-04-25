package com.example.findroomies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R
import com.example.findroomies.ui.adapters.PagerAdapter

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // Call before setting content view
        setContentView(R.layout.activity_splash)

    }

    private fun isUserOnboarded(): Boolean {
        val sharedPref = getSharedPreferences("onboarding", MODE_PRIVATE)
        return sharedPref.getBoolean("onboarded", true)
    }

    private fun navigateToLoginPage() {

    }
}