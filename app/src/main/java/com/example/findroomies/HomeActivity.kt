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
            RoomModel("Lovely Lovely Room","190 fleming drive", "$450/month",false,FurnishingType.FULLY_FURNISHED,PropertyType.HOUSE),
            RoomModel("Condo Condo Condo","762 Maitland Street", "$990/month",true,FurnishingType.SEMI_FURNISHED,PropertyType.CONDO),
            )
        val roomAdapter:RoomAdapter = RoomAdapter(rooms, this);

        recyclerView.adapter=roomAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)

    }

    override fun onRoomItemClick(room: RoomModel) {
        val intent: Intent = Intent(this,RoomDetailActivity::class.java)
        Log.d("LOG CLICK",room.address);
        Log.d("LOG CLICK TITLE",room.title);
        startActivity(intent)
    }
}