package com.example.findroomies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(),OnRoomItemClickInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val recyclerView= findViewById<RecyclerView>(R.id.recycler_view_id)
        val rooms= mutableListOf(
            RoomModel("Lovely Lovely Room","190 fleming drive, London, Ontario, Canada, ", "$450/month","https://images.pexels.com/photos/1669799/pexels-photo-1669799.jpeg?cs=srgb&dl=pexels-vecislavas-popa-1669799.jpg&fm=jpg",false,FurnishingType.FULLY_FURNISHED,PropertyType.HOUSE),
            RoomModel("Condo Condo Condo","762 Maitland Street", "$990/month","https://images.pexels.com/photos/1669799/pexels-photo-1669799.jpeg?cs=srgb&dl=pexels-vecislavas-popa-1669799.jpg&fm=jpg",true,FurnishingType.SEMI_FURNISHED,PropertyType.CONDO),
            )
        val roomAdapter = RoomAdapter(rooms, this)

        recyclerView.adapter=roomAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)

    }

    override fun onRoomItemClick(room: RoomModel) {
//        val intent = Intent(this,RoomDetailActivity::class.java)
        Log.d("LOG CLICK",room.address)
        Log.d("LOG CLICK TITLE",room.title)
//        startActivity(intent)
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
        menuInflater.inflate(R.menu.home_appbar, menu)
        return true
    }
}