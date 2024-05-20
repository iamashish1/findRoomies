package com.example.findroomies.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R
import com.example.findroomies.databinding.ActivityMainBinding
import com.example.findroomies.databinding.ActivitySplashBinding
import com.example.findroomies.listeners.FirebaseAuthManager
import com.example.findroomies.listeners.ToastMessageListener
import com.example.findroomies.ui.adapters.PagerAdapter
import com.example.findroomies.ui.viewmodels.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity @Inject constructor()  : AppCompatActivity(), ToastMessageListener {

    private lateinit var binding: ActivitySplashBinding
    @Inject
    lateinit var auth: FirebaseAuth

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuthManager.removeAuthStateListener(authStateListener)

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
       binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseAuthManager.addAuthStateListener(authStateListener)

    }

    override fun showToast(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

}