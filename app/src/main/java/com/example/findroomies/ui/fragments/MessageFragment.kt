package com.example.findroomies.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findroomies.R
import com.example.findroomies.data.model.MessageListModel
import com.example.findroomies.databinding.FragmentMessageBinding
import com.example.findroomies.listeners.MessageClickListener
import com.example.findroomies.ui.activities.ChatActivity
import com.example.findroomies.ui.activities.RoomDetailActivity
import com.example.findroomies.ui.adapters.MessageListAdapter
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.viewmodels.MessageViewModel
import com.example.findroomies.ui.viewmodels.RoomViewModel


class MessageFragment : Fragment(), MessageClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: MessageListAdapter
private lateinit var messageViewModel: MessageViewModel
private  var messages:MutableList<MessageListModel> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  view= inflater.inflate(R.layout.fragment_message, container, false)
        recyclerView = view.findViewById(R.id.messageListRv)
        val fragmentContext = requireContext()

        mAdapter = MessageListAdapter(messages,this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentContext)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageViewModel = ViewModelProvider(this)[MessageViewModel::class.java]

        messageViewModel.messages.observe(viewLifecycleOwner){
           messages.clear()
            messages.addAll(it)
            mAdapter.notifyDataSetChanged()

        }
    }

    override fun onMessageItemClick(documentId: String) {
        val intent = Intent(requireActivity(), ChatActivity::class.java)
        val bundle = Bundle().apply {
            putString("DOCUMENT_ID", documentId)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }


}