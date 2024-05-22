package com.example.findroomies.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findroomies.R
import com.example.findroomies.listeners.BookmarkListener
import com.example.findroomies.listeners.ConversationClickListener
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.ui.activities.ChatActivity
import com.example.findroomies.ui.activities.RoomDetailActivity
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.viewmodels.RoomViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnRoomItemClickInterface, ConversationClickListener,BookmarkListener {
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        loadingIndicator = view.findViewById(R.id.loading_indicator)
        recyclerView = view.findViewById(R.id.recycler_view_id)
        val fragmentContext = requireContext()

        roomAdapter = RoomAdapter(mutableListOf(),this, this,this)
        recyclerView.adapter = roomAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentContext)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomViewModel = ViewModelProvider(this, RoomViewModel.Factory)[RoomViewModel::class.java]
        roomViewModel.rooms.observe(viewLifecycleOwner) {
            roomAdapter.updateRooms(it)
            loadingIndicator.visibility = View.GONE
        }
        //USE TEXTWATCHER TO WATCH CHANGES
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // this function is called before text is edited

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                roomViewModel.filterRooms(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                // this function is called after text is edited
            }
        }

        val search=view.findViewById<EditText>(R.id.editTextText1) // textWatcher is for watching any changes in editText
        search.addTextChangedListener(textWatcher)


        roomViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                loadingIndicator.visibility = View.VISIBLE
            } else {
                loadingIndicator.visibility = View.GONE
            }
        }


    }

    override fun onRoomItemClick(documentId: String) {
        val intent = Intent(requireActivity(), RoomDetailActivity::class.java)
        val bundle = Bundle().apply {
            putString("DOCUMENT_ID", documentId)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun startConversation(receiver: String) {
        val intent = Intent(requireActivity(), ChatActivity::class.java)
        val bundle = Bundle().apply {
            putString("RECEIVER_ID", receiver)
        }
        intent.putExtras(bundle)
        startActivity(intent)

    }

    override fun bookmark(roomId: String) {
        roomViewModel.bookmarkRoom(roomId)

    }


}