package com.example.findroomies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findroomies.data.model.MessageModel
import com.example.findroomies.databinding.ReceiverLayoutBinding
import com.example.findroomies.databinding.SenderLayoutBinding

class MessageAdapter(private val msgs: MutableList<MessageModel>, private val userId: String ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        return if (msgs[position].from==userId) {
            0
        } else {
            1
        }
    }

    inner class  ReceiverViewHolder(val binding: ReceiverLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    inner class SenderViewHolder(val binding: SenderLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding= SenderLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            SenderViewHolder(binding)
        } else {
            val binding = ReceiverLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            ReceiverViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return msgs.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMsg = msgs[position]
        if (holder is SenderViewHolder) {
            holder.binding.msg.text = currentMsg.msg

        }else if (holder is ReceiverViewHolder) {
            holder.binding.msg.text = currentMsg.msg
        }
    }
}