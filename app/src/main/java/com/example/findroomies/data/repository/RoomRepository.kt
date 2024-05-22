package com.example.findroomies.data.repository

import com.example.findroomies.data.model.RoomModel


interface RoomRepository {

    suspend fun getRoomDetail(documentId:String): RoomModel?
    suspend fun getRooms(): List<RoomModel>

    suspend fun bookmarkRoom(romId:String): RoomModel?
    suspend fun addRoom(room: RoomModel)
    suspend fun updateRoom(room: RoomModel)
    suspend fun deleteRoom(roomId: String)
}
