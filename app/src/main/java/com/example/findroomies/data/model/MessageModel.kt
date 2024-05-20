package com.example.findroomies.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
class MessageModel(
    val from: String?=null,
    val to: String?=null,
    val timestamp: Timestamp?=null,
    val msg: String?=null,
)