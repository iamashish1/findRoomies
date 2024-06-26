package com.example.findroomies.data.repository

import android.content.ContentValues
import android.util.Log
import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class RoomRepositoryImpl : RoomRepository {
    private val db = FirebaseFirestore.getInstance()
    override suspend fun getRoomDetail(documentId:String): RoomModel? {
        return try {
            val docRef = db.collection("Rooms").document("$documentId")
            val document = docRef.get().await()
            if (document.exists()) {
                // Assuming the document data can be converted to RoomModel
                document.toObject(RoomModel::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
           throw e
        }
    }

    override suspend fun getRooms(): List<RoomModel> {
        val rooms = mutableListOf<RoomModel>()
        println(FirebaseAuth.getInstance().currentUser?.uid)
        try {
            val querySnapshot = db.collection("Rooms").whereNotEqualTo("addedBy.userId",FirebaseAuth.getInstance().currentUser?.uid).get().await()
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

    override suspend fun getBookmarkedRooms(): List<RoomModel>? {
var bRoms: List<RoomModel>? = mutableListOf()
        try {
            val query = db.collection("Rooms")
                .whereArrayContains("bookmarkedBy", FirebaseAuth.getInstance().currentUser?.uid?:"")
           var docs= query.get().await()

            bRoms=  docs.map {
                println(it.data.toString() +"String")
              it.toObject(RoomModel::class.java)

            }.toList()


            return  bRoms;

        }catch (e:Exception){
            throw  e;

        }

    }

    override suspend fun bookmarkRoom(roomId: String): RoomModel? {
        try {

            val docRef= db.collection("Rooms").document(roomId)

            val document= docRef.get().await()

            if(document.exists()){
                val currentUserId= FirebaseAuth.getInstance().currentUser?.uid
                val bookmarkedBy = document.get("bookmarkedBy") as? List<String> ?: emptyList()

                if(bookmarkedBy.contains(currentUserId)){
                    docRef.update("bookmarkedBy", FieldValue.arrayRemove(currentUserId)).await()

                }else{
                    docRef.update("bookmarkedBy", FieldValue.arrayUnion(currentUserId)).await()

                }
            }



            val updatedDocument = docRef.get().await()
            println(roomId)
            println(updatedDocument.data)
            return updatedDocument.toObject(RoomModel::class.java)?:null

        }catch (e:Exception){
         throw e
        }

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