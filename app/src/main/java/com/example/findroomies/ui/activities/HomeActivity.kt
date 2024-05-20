package com.example.findroomies.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.findroomies.ui.fragments.MessageFragment
import com.example.findroomies.ui.fragments.NotificationFragment
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.ui.fragments.ProfileFragment
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.listeners.FirebaseAuthManager
import com.example.findroomies.ui.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        FirebaseAuthManager.addAuthStateListener(authStateListener)

        // Find the included layout
//        val includeLayout = findViewById<View>(R.id.include)
//        val toolbar = includeLayout.findViewById<MaterialToolbar>(R.id.materialToolbar3)
//        setSupportActionBar(toolbar)
//        toolbar.title="Roomates"
//        toolbar.textAlignment= View.TEXT_ALIGNMENT_CENTER

        //LOAD DEFAULT FRAGMENT
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.fav -> {
                    loadFragment(MessageFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(NotificationFragment())
                    true
                }
                R.id.profile ->{
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    //ELSE LOAD DEFAULT FRAGMENT
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }





//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.home_appbar_menu_items, menu)
//        return true
//    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }
}