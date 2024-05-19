package com.example.findroomies.ui.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.data.model.MessageListModel
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MessageViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _messages = MutableLiveData<List<MessageListModel>>()
    val messages: LiveData<List<MessageListModel>> get() = _messages
    private val auth = FirebaseAuth.getInstance()
    init {
        getAllMessages()
    }

    private fun getAllMessages() {

        viewModelScope.launch {
            try {
                val querySnapshot = db.collection("Message")
                    .whereArrayContains("participants", auth.currentUser?.uid?:"")
                    .get()
                    .await()

                val messageList = querySnapshot.documents.mapNotNull {
                    it.toObject(MessageListModel::class.java)
                }
                _messages.value = messageList
            } catch (e: Exception) {
                Log.w(ContentValues.TAG, "Error getting documents.", e)
            }
        }
    }
}