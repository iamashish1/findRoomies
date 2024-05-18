package com.example.findroomies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.databinding.ActivityRoomDetailBinding
import com.example.findroomies.databinding.ActivitySplashBinding
import com.example.findroomies.ui.viewmodels.RoomViewModel

class RoomDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomDetailBinding
    private lateinit var roomViewModel: RoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val documentId = bundle?.getString("DOCUMENT_ID")

        roomViewModel = ViewModelProvider(this, RoomViewModel.Factory)[RoomViewModel::class.java]
        // Use the documentId to fetch room details
        if (documentId != null) {
            roomViewModel.getRoomDetail(documentId)
        }


        binding.backButton.setOnClickListener(){
            finish()
        }

        // Observe ROOM DATA
        roomViewModel.singleRoom.observe(this) {
            // Update RecyclerView adapter with new data

            updateUI(it)

        }

    }

    private fun updateUI(room: RoomModel?) {
        binding.price.text=room?.rent?:""
        binding.addedBy.text=room?.addedBy?.name?:""
        binding.address.text=room?.address
        binding.description.text=room?.description
        binding.propertyDetail.text=room?.houseType?.name?:""


    }
}