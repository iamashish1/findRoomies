package com.example.findroomies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findroomies.R
import com.example.findroomies.databinding.FragmentNotificationBinding
import com.example.findroomies.databinding.FragmentProfileBinding
import com.example.findroomies.listeners.BookmarkListener
import com.example.findroomies.listeners.ConversationClickListener
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.viewmodels.ProfileViewModel
import com.example.findroomies.ui.viewmodels.RoomViewModel


class NotificationFragment : Fragment(), OnRoomItemClickInterface, ConversationClickListener,
    BookmarkListener {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var roomVideModel: RoomViewModel
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotificationBinding.inflate(inflater)
        recyclerView = binding.rvBM
        val fragmentContext = requireContext()
        roomAdapter = RoomAdapter(mutableListOf(),this, this,this)
        recyclerView.adapter = roomAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentContext)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomVideModel= ViewModelProvider(this,RoomViewModel.Factory)[RoomViewModel::class.java]
        // In your activity or fragment

        roomVideModel.getBookmarkedRooms()

        roomVideModel.bookmarkedRooms.observe(viewLifecycleOwner) { bRoom ->
          bRoom?.let {
              roomAdapter.updateRooms(it)

          }
        }
    }

    override fun bookmark(roomId: String) {
        TODO("Not yet implemented")
    }

    override fun startConversation(receiver: String) {
        TODO("Not yet implemented")
    }

    override fun onRoomItemClick(documentId: String) {
        TODO("Not yet implemented")
    }


}