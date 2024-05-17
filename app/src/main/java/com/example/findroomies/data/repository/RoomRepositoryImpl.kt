package com.example.findroomies.data.repository

import android.content.ContentValues
import android.util.Log
import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RoomRepositoryImpl : RoomRepository {
    private val db = FirebaseFirestore.getInstance()

    override suspend fun getRooms(): List<RoomModel> {
        val rooms = mutableListOf<RoomModel>()
        try {
            val querySnapshot = db.collection("Rooms").get().await()
            for (document in querySnapshot.documents) {
                val room = RoomModel(
                    id = document.id,
                    title = document.getString("title") ?: "",
                    address = document.getString("address") ?: "",
                    rent = document.getString("rent") ?: "",
                    imageUrl = document.get("imageUrl")  as List<String?>?,
                    furnishingType = document.get("furnishingType", FurnishingType::class.java)
                        ?: FurnishingType.UNKNOWN,
                    isUtilityIncluded = document.getBoolean("isUtilityIncluded") ?: false,
                    houseType = document.get("houseType", PropertyType::class.java)
                        ?: PropertyType.UNKNOWN,
                    addedBy = document.get("addedBy", UserModel::class.java),
                    bookmarkedBy = document.get("bookmarkedBy") as List<String?>?,
                    timestamps = document.getTimestamp("timestamps"),
                    description = document.getString("description") ?:""
                )
                rooms.add(room)
            }
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", e)
        }
        return rooms
    }
    override suspend fun addRoom(room: RoomModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRoom(room: RoomModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRoom(roomId: String) {
        TODO("Not yet implemented")
    }
}