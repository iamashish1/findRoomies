package com.example.findroomies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findroomies.data.model.MessageListModel
import com.example.findroomies.databinding.MessageItemBinding
import com.example.findroomies.databinding.RoomItemBinding
import com.example.findroomies.listeners.MessageClickListener
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.ui.viewholder.MessageListViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageListAdapter(private val messages: List<MessageListModel>,
                         private var clickListener: MessageClickListener

) : RecyclerView.Adapter<MessageListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MessageListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messages.size;
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        if(messages[position].receiver?.id==FirebaseAuth.getInstance().currentUser?.uid){
            holder.binding?.name?.text = messages[position].sender?.name
            holder.binding?.lastMessage?.text = messages[position].lastMessage

        }else{
            holder.binding?.name?.text = messages[position].receiver?.name
            holder.binding?.lastMessage?.text = messages[position].lastMessage

        }

        holder.binding.root.setOnClickListener(){
            clickListener.onMessageItemClick(messages[position]?.id?:"")

        }

    }
}



