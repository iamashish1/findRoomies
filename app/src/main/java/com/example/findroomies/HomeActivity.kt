package com.example.findroomies

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeActivity : AppCompatActivity(),OnRoomItemClickInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val db = Firebase.firestore
        val loadingIndicator = findViewById<ProgressBar>(R.id.loading_indicator)
        loadingIndicator.visibility= View.VISIBLE
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val recyclerView= findViewById<RecyclerView>(R.id.recycler_view_id)
        val rooms = mutableListOf<RoomModel>()

        val roomAdapter = RoomAdapter(rooms, this)

        recyclerView.adapter=roomAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)

        //START READ DATA FROM FIREBASE

        db.collection("Rooms")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val room = RoomModel(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        address = document.getString("address") ?: "",
                        rent = document.getString("rent") ?: "",
                        imageAddress = document.getString("imageUrl") ?: "",
                        furnishingType = document.get("furnishingType", FurnishingType::class.java) ?: FurnishingType.UNKNOWN,
                        isUtilityIncluded = document.getBoolean("isUtilityIncluded") ?: false,
                        houseType = document.get("houseType", PropertyType::class.java) ?: PropertyType.UNKNOWN
                    )
                    rooms.add(room)
                }
                loadingIndicator.visibility= View.GONE
                roomAdapter.notifyDataSetChanged() // Notify adapter of data change
            }
            .addOnFailureListener { exception ->
                loadingIndicator.visibility= View.GONE

                Log.w(TAG, "Error getting documents.", exception)
            }

        //END READ DATA FROM FIREBASE




    }

    override fun onRoomItemClick(room: RoomModel) {
        val intent = Intent(this,RoomDetailActivity::class.java)
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
        menuInflater.inflate(R.menu.home_appbar, menu)
        return true
    }
}