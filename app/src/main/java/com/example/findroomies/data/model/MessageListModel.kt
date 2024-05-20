package com.example.findroomies.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class MessageListModel(
    val id: String?=null,
    val lastMessage: String?=null,
    val participants: List<String>?=null,
    val p1:User?=null,
    val p2:User?=null

)


data class User(
   val id:String?=null,
    val name:String?=null,
    val profilePic:String?=null
){}


