package com.example.findroomies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.findroomies.R
import com.example.findroomies.ui.adapters.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_onboardings, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val adapter = PagerAdapter(requireActivity())
        viewPager.adapter = adapter
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}