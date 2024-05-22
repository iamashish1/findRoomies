package com.example.findroomies.ui.adapters

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.R
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.databinding.FragmentHomeBinding
import com.example.findroomies.databinding.RoomItemBinding
import com.example.findroomies.listeners.BookmarkListener
import com.example.findroomies.listeners.ConversationClickListener
import com.example.findroomies.ui.fragments.HomeFragment
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.Period


class RoomAdapter(
    private var rooms:MutableList<RoomModel>,
    private var clickInterface: OnRoomItemClickInterface,
    private var startConversation: ConversationClickListener,
    private var bookmarkListner: BookmarkListener
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        //FOR THE TYPE OF VIEW FOR ELEMENT AT POSITION
        return super.getItemViewType(position)
    }

    inner class RoomViewHolder(binding: RoomItemBinding): RecyclerView.ViewHolder(binding.root){
         var tvTitle: TextView
         var messageButton: ImageButton
        var bookmarkBtn: ImageButton
         var ivRoomImage: ImageView
         var tvRent: TextView
         var addedBy: TextView
         var description: TextView
         var timeAdded: TextView

        init {
            bookmarkBtn= binding.bookmarkButton
           messageButton=binding.messageButton
            addedBy= binding.addedByText
            tvRent= binding.rentText
            tvTitle= binding.titleId
            ivRoomImage= binding.ivRoomImage
            description= binding.descriptionText
            timeAdded= binding.timeAddedText


            binding.messageButton.setOnClickListener(){
                startConversation.startConversation(rooms[adapterPosition].addedBy?.userId?:"")
            }

            binding.bookmarkButton.setOnClickListener(){
                bookmarkListner.bookmark(rooms[adapterPosition].id?:"")
            }


            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickInterface.onRoomItemClick(rooms[position]?.id?:"")
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {

        val binding =
            RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        // Load image into ImageView using Coil
        holder.ivRoomImage.load(rooms[position].imageUrl?.get(0) ?:"") {
            // Optionally, you can configure Coil's image loading options here
            placeholder(R.drawable.flat_button) // Placeholder drawable while loading
            error(R.drawable.placeholder_image) // Error drawable if loading fails
            crossfade(true) // Enable crossfade animation
            // And more options...
        }

        val color = ContextCompat.getColor(holder.itemView.context, R.color.primary_amber)
    if(rooms[position].bookmarkedBy!!.contains(FirebaseAuth.getInstance().uid)){
        ImageViewCompat.setImageTintList(holder.bookmarkBtn, ColorStateList.valueOf(color))
    }else {
        ImageViewCompat.setImageTintList(holder.bookmarkBtn, null)
    }


        holder.tvTitle.text = rooms[position].title
        holder.tvRent.text= rooms[position].rent
        holder.description.text= rooms[position].description
        holder.addedBy.text= rooms[position].addedBy?.name ?: ""


        val timestampMillis = rooms[position].timestamps?.toDate()?.toInstant()
        val now = Instant.now()

        val timeAddedText = timestampMillis?.let {
            val duration = Duration.between(timestampMillis, now)
            val hours = (duration.toHours())
            val minutes = (duration.toMinutes() %60)

            when {
                hours>24 -> "${(hours/24).toInt()} day(s) ago"
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