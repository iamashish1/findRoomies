package com.example.findroomies.ui.fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.listeners.OnRoomItemClickInterface
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.R
import com.example.findroomies.ui.adapters.RoomAdapter
import com.example.findroomies.ui.activities.RoomDetailActivity
import com.example.findroomies.data.model.RoomModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnRoomItemClickInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadingIndicator = view.findViewById<ProgressBar>(R.id.loading_indicator)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recycler_view_id)
       val fragmentContext=requireContext()
        val db = Firebase.firestore
        loadingIndicator.visibility= View.VISIBLE
        val rooms = mutableListOf<RoomModel>()

        val roomAdapter = RoomAdapter(rooms, this)

        recyclerView.adapter=roomAdapter
        recyclerView.layoutManager= LinearLayoutManager(fragmentContext)

        db.collection("Rooms")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val room = RoomModel(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        address = document.getString("address") ?: "",
                        rent = document.getString("rent") ?: "",
                        imageUrl = document.getString("imageUrl") ?: "",
                        furnishingType = document.get("furnishingType", FurnishingType::class.java)
                            ?: FurnishingType.UNKNOWN,
                        isUtilityIncluded = document.getBoolean("isUtilityIncluded") ?: false,
                        houseType = document.get("houseType", PropertyType::class.java)
                            ?: PropertyType.UNKNOWN
                    )
                    rooms.add(room)

                }
                loadingIndicator.visibility = View.GONE
                roomAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                loadingIndicator.visibility = View.GONE
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("ARG_PARAM1")
            param2 = it.getString("ARG_PARAM2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }

    override fun onRoomItemClick(room: RoomModel) {
        val intent = Intent(requireActivity(), RoomDetailActivity::class.java)
        startActivity(intent)
    }
}