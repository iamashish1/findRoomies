package com.example.findroomies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R
import com.example.findroomies.databinding.ActivityMainBinding
import com.example.findroomies.databinding.ActivitySplashBinding
import com.example.findroomies.ui.adapters.PagerAdapter
import com.example.findroomies.ui.viewmodels.AuthenticationViewModel



class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // Call before setting content view
       binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }


}