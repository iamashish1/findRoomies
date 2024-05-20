package com.example.findroomies.ui.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.data.model.MessageListModel
import com.example.findroomies.data.model.MessageModel
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.model.UserModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date

class MessageViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _messages = MutableLiveData<List<MessageListModel>>()


    val messages: LiveData<List<MessageListModel>> get() = _messages
    private val auth = FirebaseAuth.getInstance()

    private val _conversation= MutableLiveData<List<MessageModel>>()
    val conversation: LiveData<List<MessageModel>> get() = _conversation

    init {
        getAllMessages()
    }

    fun sendMessage(msgText: String, to: String) {
        viewModelScope.launch {

            try {
                val currentUserId = auth.currentUser?.uid ?: return@launch
                println(currentUserId)
                println(to)
                // Check if a conversation document already exists between currentUserId and to
                val querySnapshot = db.collection("Message")
                    .whereArrayContainsAny("participants", listOf(currentUserId,to))
                    .get()
                    .await()

                if (!querySnapshot.isEmpty) {
                    val documentId = querySnapshot.documents.first().id
                    val message = MessageModel(
                        from = currentUserId,
                        to = to,
                        msg = msgText,
                        timestamp = Timestamp.now()
                    )

                    db.collection("Message")
                        .document(documentId)
                        .collection("Message")
                        .add(message)
                        .await()

                    db.collection("Message")
                        .document(documentId)
                        .update("lastMessage", msgText)
                        .await()
                } else {
                    val participants = listOf(currentUserId, to)
                    val p1 = hashMapOf(
                        "id" to to,
                        "name" to "",
                        "profilePic" to ""
                    )
                    val p2 = hashMapOf(
                        "id" to currentUserId,
                        "name" to "",
                        "profilePic" to ""
                    )
                    val conversationData = hashMapOf(
                        "participants" to participants,
                        "lastMessage" to msgText,
                        "p1" to p1,
                        "p2" to p2
                    )

                    val conversationRef = db.collection("Message").document()
                    conversationRef.set(conversationData)
                        .addOnSuccessListener {
                            val message = MessageModel(
                                from = currentUserId,
                                to = to,
                                msg = msgText,
                                timestamp = Timestamp.now()
                            )
                            conversationRef.collection("Message")
                                .add(message)
                                .addOnSuccessListener {
                                    conversationRef.update("lastMessage", msgText)
                                }
                        }
                        .await()
                }
            } catch (e: Exception) {
                Log.w(ContentValues.TAG, "Error sending message.", e)
            }
        }
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

    fun getConversation(participantId: String) {
        viewModelScope.launch {
            try {
                val currentUserId = auth.currentUser?.uid ?: return@launch

                val query = db.collection("Message")
                    .whereArrayContains("participants", currentUserId)
                    .whereArrayContains("participants", participantId)

                query.addSnapshotListener { querySnapshot, _ ->
                    if (querySnapshot != null) {
                        val conversationDoc = querySnapshot.documents.firstOrNull()
                        if (conversationDoc != null) {
                            val messageCollectionRef = conversationDoc.reference.collection("Message")
                            messageCollectionRef.addSnapshotListener { messageSnapshot, _ ->
                                if (messageSnapshot != null) {
                                    val messages = messageSnapshot.documents.mapNotNull {
                                        it.toObject(MessageModel::class.java)
                                    }.sortedBy {
                                        it.timestamp
                                    }
                                    _conversation.value = messages
                                }
                            }
                        } else {
                            _conversation.value = emptyList()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.w(ContentValues.TAG, "Error getting documents.", e)
            }
        }
    }


}