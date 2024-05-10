package com.example.findroomies.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {

     val emailText = ObservableField<String>("")
     val passwordText = ObservableField<String>("")


    fun onSubmitClicked() {
        // Handle submit action
        println("SIGN IN BUTTON CLICKED ${emailText.get()} ${passwordText.get()}")

    }

    fun isValidInput(): Boolean {
        val inputEm = emailText.get() ?: return false
        val inputPass = passwordText.get() ?: return false

        return inputEm.isNotEmpty() && inputPass.isNotEmpty()
    }

}