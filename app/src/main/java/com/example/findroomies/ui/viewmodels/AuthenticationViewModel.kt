package com.example.findroomies.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {

     val emailText = ObservableField<String>("")
     val passwordText = ObservableField<String>("")
     val nameText= ObservableField<String>("")

    fun onSubmitClicked(isLogin:Boolean) {
        // Handle submit action
        if(isLogin){
            println("Login  BUTTON CLICKED ${emailText.get()} ${passwordText.get()}")

        }else{
            println("SIGN Up BUTTON CLICKED ${emailText.get()} ${passwordText.get()}")

        }
    }

    fun isValidInput(): Boolean {
        val inputEm = emailText.get() ?: return false
        val inputPass = passwordText.get() ?: return false

        return inputEm.isNotEmpty() && inputPass.isNotEmpty()
    }

}