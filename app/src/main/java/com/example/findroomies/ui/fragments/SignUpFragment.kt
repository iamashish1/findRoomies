package com.example.findroomies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.findroomies.R


class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInText = view.findViewById<TextView>(R.id.sign_in_text)

        signInText.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val signInFragemnt = SignInFragment()
            val transaction = fragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in, // enter
                R.anim.fade_out, // exit
//                R.anim.fade_in, // popEnter
//                R.anim.slide_out // popExit
            )
            transaction.replace(R.id.fragmentContainerView2, signInFragemnt)
            transaction.commit()
        }
    }


}