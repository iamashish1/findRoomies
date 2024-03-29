package com.example.findroomies.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.example.findroomies.ui.fragments.FavoriteFragment
import com.example.findroomies.ui.fragments.MessageFragment
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.ui.fragments.ProfileFragment
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.ui.fragments.HomeFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), OnRoomItemClickInterface {
    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Find the included layout
        val includeLayout = findViewById<View>(R.id.include)
        val toolbar = includeLayout.findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        //LOAD DEFAULT FRAGMENT
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.fav -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(MessageFragment())
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

    override fun onRoomItemClick(room: RoomModel) {
        val intent = Intent(this, RoomDetailActivity::class.java)
        Log.d("LOG CLICK",room.address)
        Log.d("LOG CLICK TITLE",room.title)
        startActivity(intent)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
////        return when (item.itemId) {
////            R.id.action_settings -> {
////                // Handle search action
////                true
////            }
////            // Add cases for other menu items as needed
////            else -> super.onOptionsItemSelected(item)
////        }
//    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_appbar_menu_items, menu)
        return true
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }
}