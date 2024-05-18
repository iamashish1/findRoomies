package com.example.findroomies.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.databinding.FragmentHomeBinding
import com.example.findroomies.databinding.RoomItemBinding
import com.example.findroomies.ui.fragments.HomeFragment
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.Period


class RoomAdapter(
    private var rooms:MutableList<RoomModel>,
    private var clickInterface: OnRoomItemClickInterface
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        //FOR THE TYPE OF VIEW FOR ELEMENT AT POSITION
        return super.getItemViewType(position)
    }

    inner class RoomViewHolder(binding: RoomItemBinding): RecyclerView.ViewHolder(binding.root){
         var tvTitle: TextView
         var ivRoomImage: ImageView
         var tvRent: TextView
         var addedBy: TextView
         var description: TextView
         var timeAdded: TextView

        init {

            addedBy= binding.addedByText
            tvRent= binding.rentText
            tvTitle= binding.titleId
            ivRoomImage= binding.ivRoomImage
            description= binding.descriptionText
            timeAdded= binding.timeAddedText



            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //NOTE: This click listener can also be set in onBindViewHolder
                    clickInterface.onRoomItemClick(rooms[position]?.id?:"")
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
//        val binding= FragmentHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return  RoomViewHolder(binding)
        val binding =
            RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item,parent,false)
//       return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        holder.ivRoomImage.load(rooms[position].imageUrl?.get(0) ?:"") {
            // Optionally, you can configure Coil's image loading options here
            placeholder(R.drawable.flat_button) // Placeholder drawable while loading
            error(R.drawable.placeholder_image) // Error drawable if loading fails
            crossfade(true) // Enable crossfade animation
            // And more options...
        }

        holder.tvTitle.text = rooms[position].title
        holder.tvRent.text= rooms[position].rent
        holder.description.text= rooms[position].description
        holder.addedBy.text= rooms[position].addedBy?.name ?: ""
//holder.timeAdded.text= rooms[position].timestamps?.toDate().let {
//    var time = it?.time
//    //time gives me 1715832000482
//    "$time"
//}
        // Assuming rooms[position].timestamps gives you a timestamp in milliseconds
        val timestampMillis = rooms[position].timestamps?.toDate()?.toInstant()
        val now = Instant.now()

        val timeAddedText = timestampMillis?.let {
            val duration = Duration.between(timestampMillis, now)
            val hours = (duration.toHours())
            val minutes = (duration.toMinutes() %60)

            when {

                hours>24 -> "${(hours%24).toInt()} day(s) ago"
                hours > 0 -> "$hours hours ago"
                hours < 1 && minutes>1 -> "$minutes minutes ago"
                else -> "Just now"
            }
        } ?: "unknown time"


        holder.timeAdded.text= timeAddedText
    }

    fun updateRooms(newRooms: List<RoomModel>) {
        rooms.clear()
        rooms.addAll(newRooms)
        notifyDataSetChanged()
    }
}