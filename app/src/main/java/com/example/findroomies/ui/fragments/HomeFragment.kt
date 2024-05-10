package com.example.findroomies.ui.fragments

import com.example.findroomies.ui.viewmodels.RoomViewModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.R
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.activities.RoomDetailActivity
import com.example.findroomies.data.model.RoomModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnRoomItemClickInterface {
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

        roomAdapter = RoomAdapter(mutableListOf(), this)
        recyclerView.adapter = roomAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentContext)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        roomViewModel = ViewModelProvider(this, RoomViewModel.Factory)[RoomViewModel::class.java]
        // Observe rooms data
        roomViewModel.rooms.observe(viewLifecycleOwner) {
            // Update RecyclerView adapter with new data
            roomAdapter.updateRooms(it)
            loadingIndicator.visibility = View.GONE
        }

        // Observe loading state
        roomViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                loadingIndicator.visibility = View.VISIBLE
            } else {
                loadingIndicator.visibility = View.GONE
            }
        }
    }

    override fun onRoomItemClick(room: RoomModel) {
        // Handle item click, if needed
        val intent = Intent(requireActivity(), RoomDetailActivity::class.java)
        startActivity(intent)
    }
}