package com.example.findroomies.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.findroomies.R
import com.example.findroomies.databinding.FragmentSignInBinding
import com.example.findroomies.databinding.FragmentSignUpBinding
import com.example.findroomies.ui.activities.HomeActivity
import com.example.findroomies.ui.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthenticationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInText = view.findViewById<TextView>(R.id.sign_in_text)
        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()

        viewModel.getNavigateToHomeActivity()?.observe(requireActivity()) { navigate ->
            if (navigate) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent).let {
                    // Reset the value to prevent re-triggering
                    viewModel.setNavigateValue(false)

                }
                requireActivity().finish()


            }
        }

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