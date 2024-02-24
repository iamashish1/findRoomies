package com.example.findroomies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter(
    private var rooms:MutableList<RoomModel>
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(roomView: View): RecyclerView.ViewHolder(roomView){
         var tvTitle: TextView
         var tvAddress: TextView

        init {
            tvTitle = roomView.findViewById(R.id.TitleId)
            tvAddress = roomView.findViewById(R.id.AddressId)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item,parent,false)
       return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
//        holder.itemView.findViewById<TextView>(R.id.TitleId).text=rooms[position].title
//        holder.itemView.findViewById<TextView>(R.id.AddressId).text=rooms[position].address
        //OR
//        holder.itemView.apply {
//            findViewById<TextView>(R.id.TitleId).text=rooms[position].title
//            findViewById<TextView>(R.id.AddressId).text=rooms[position].address
//        }
        // OR
        holder.tvTitle.text = rooms[position].title
        holder.tvAddress.text = rooms[position].address


    }
}