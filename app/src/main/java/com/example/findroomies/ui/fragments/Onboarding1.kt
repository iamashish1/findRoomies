package com.example.findroomies.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R


class Onboarding1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var nextButton: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding1, container, false)
        nextButton = view.findViewById(R.id.next) // Replace with your button's ID
        val isOnboarded= isUserOnboarded()
      return  if(isOnboarded){
            requireActivity().findNavController(R.id.fragmentContainerView2).navigate(R.id.action_onboardingsFragment_to_signInFragment)
            return null
        }else{

            view
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
            viewPager?.setCurrentItem(viewPager.currentItem + 1, true)
            //AND THEM SET USER ONBOARDED TO TRUE NO MATTER WHAT ACTION THE USER TAKES
            setUserOnboarded()
        }
    }
    private fun isUserOnboarded(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onboarding_status", MODE_PRIVATE)
        return sharedPref.getBoolean("onboarded", false)
    }

    private fun setUserOnboarded() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding_status", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("onboarded", true)
            apply()
        }
    }




}