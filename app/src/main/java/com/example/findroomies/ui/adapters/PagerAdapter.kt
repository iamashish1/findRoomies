package com.example.findroomies.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.findroomies.ui.fragments.Onboarding1
import com.example.findroomies.ui.fragments.Onboarding2
import com.example.findroomies.ui.fragments.Onboarding3

class PagerAdapter( frag: FragmentActivity) : FragmentStateAdapter(frag) {
    private val fragmentList = listOf<Fragment>(Onboarding1(),Onboarding2(),Onboarding3())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragmentList[position]
    }
}
