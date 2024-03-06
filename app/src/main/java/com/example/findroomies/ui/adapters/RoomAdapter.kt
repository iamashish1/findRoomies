package com.example.findroomies.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel

class RoomAdapter(
    private var rooms:MutableList<RoomModel>,
    private var clickInterface: OnRoomItemClickInterface
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {


    inner class RoomViewHolder(roomView: View): RecyclerView.ViewHolder(roomView){
         var tvTitle: TextView
         var tvAddress: TextView
         var ivRoomImage: ImageView
         var tvRent: TextView
         var tvUtility: TextView
         var tvFurnishing: TextView
         var tvHouseType: TextView

        init {
            tvTitle = roomView.findViewById(R.id.TitleId)
            tvAddress = roomView.findViewById(R.id.AddressId)
            tvRent= roomView.findViewById(R.id.priceId)
            ivRoomImage= roomView.findViewById(R.id.imageView)
            tvUtility= roomView.findViewById(R.id.utilityId)
            tvFurnishing= roomView.findViewById(R.id.furnishId)
            tvHouseType= roomView.findViewById(R.id.houseTypeId)




            roomView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //NOTE: This click listener can also be set in onBindViewHolder
                    clickInterface.onRoomItemClick(rooms[position])
                }
            }

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
        // Load image into ImageView using Coil
        holder.ivRoomImage.load(rooms[position].imageUrl) {
            // Optionally, you can configure Coil's image loading options here
            placeholder(R.drawable.flat_button) // Placeholder drawable while loading
            error(R.drawable.ic_launcher_background) // Error drawable if loading fails
            crossfade(true) // Enable crossfade animation
            // And more options...
        }


        holder.tvTitle.text = rooms[position].title
        holder.tvAddress.text = rooms[position].address
        holder.tvRent.text = rooms[position].rent
        holder.tvUtility.text = if (rooms[position].isUtilityIncluded) {
            "Utility Included"
        } else {
            "Utility Not Included"
        }

        holder.tvFurnishing.text = rooms[position].furnishingType.toString()
        holder.tvHouseType.text = rooms[position].houseType.toString()

    }
}