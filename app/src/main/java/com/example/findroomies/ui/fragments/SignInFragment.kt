package com.example.findroomies.ui.fragments

import android.app.Activity
import android.content.Context
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.findroomies.R
import com.example.findroomies.databinding.FragmentSignInBinding
import com.example.findroomies.listeners.ToastMessageListener
import com.example.findroomies.ui.viewmodels.AuthenticationViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: AuthenticationViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.signUpText.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val signUpFragment = SignUpFragment()
            val transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(
                R.anim.slide_in, // enter
                R.anim.fade_out, // exit
//                R.anim.fade_in, // popEnter
//                R.anim.slide_out // popExit
            )
            transaction.replace(R.id.fragmentContainerView2, signUpFragment) // Ensure correct ID
            transaction.commit()
        }


    }





}