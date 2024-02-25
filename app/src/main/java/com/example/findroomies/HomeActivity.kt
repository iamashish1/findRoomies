package com.example.findroomies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(),OnRoomItemClickInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val recyclerView= findViewById<RecyclerView>(R.id.recycler_view_id)
        var rooms= mutableListOf<RoomModel>(
            RoomModel("Lovely Room","190 fleming drive"),
            RoomModel("Not Good","762 Maitland Street")
        )
        val roomAdapter:RoomAdapter = RoomAdapter(rooms, this);

        recyclerView.adapter=roomAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)

    }

    override fun onRoomItemClick(room: RoomModel) {
        val intent: Intent = Intent(this,RoomDetailActivity::class.java)
        Log.d("alkdmaslkdm",room.address);
        Log.d("alkdmaslkdm",room.title);

        startActivity(intent)
    }
}