package com.example.findroomies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findroomies.R
import com.example.findroomies.data.model.MessageModel
import com.example.findroomies.databinding.ActivityChatBinding
import com.example.findroomies.ui.adapters.MessageAdapter
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.viewmodels.MessageViewModel
import com.example.findroomies.ui.viewmodels.RoomViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class ChatActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChatBinding
    private lateinit var msgViewModel: MessageViewModel

    private lateinit var msgAdapter: MessageAdapter
    private lateinit var conversationMsg:MutableList<MessageModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        conversationMsg= mutableListOf()
        val bundle = intent.extras
        val receiverId = bundle?.getString("RECEIVER_ID")
        val currentUserId= FirebaseAuth.getInstance().currentUser?.uid
        msgAdapter= MessageAdapter(conversationMsg,currentUserId?:"")
        binding.msgRV.adapter= msgAdapter
        binding.msgRV.layoutManager= LinearLayoutManager(this)



        binding.goBack.setOnClickListener(){
            finish()
        }

        // Initialize ViewModel
        msgViewModel = ViewModelProvider(this)[MessageViewModel::class.java]

        //CALL CONVERSATION LOAD FUNCTION
        msgViewModel.getConversation(receiverId?:"")
        // Observe rooms data
        msgViewModel.conversation.observe(this) {
            // Update RecyclerView adapter with new data
            conversationMsg.clear()
            conversationMsg.addAll(it)
            msgAdapter.notifyDataSetChanged()
            binding.msgRV.scrollToPosition(conversationMsg.lastIndex)
        }

        binding.imageButton.setOnClickListener(){
            msgViewModel.sendMessage( binding.editTextText1.text.toString(),receiverId?:"")
            binding.editTextText1.text.clear()

        }


    }
}

