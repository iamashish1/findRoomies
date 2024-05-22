package com.example.findroomies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.findroomies.R
import com.example.findroomies.databinding.FragmentProfileBinding
import com.example.findroomies.ui.viewmodels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

private lateinit  var  profileViewModel:ProfileViewModel
private  lateinit var binding : FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel= ViewModelProvider(this)[ProfileViewModel::class.java]
        // In your activity or fragment

        profileViewModel.profileData.observe(viewLifecycleOwner) { userProfile ->
            userProfile?.let {user ->
                // Update your UI with user profile data
                binding.let {
                    it.nameT.text=user.name
                    it.emailT.text=user.email
                    it.phoneT.text=user.phone
                    it.addressT.text=user.address?.city
                    it.bioT.text=user.bio

                }

            } ?: run {
                // Handle the case where user profile data is null
                println("MAYBE YOU ARE UNAUTHENTICATED:")

            }
        }

        binding.logoutButton.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()

        }

    }


}