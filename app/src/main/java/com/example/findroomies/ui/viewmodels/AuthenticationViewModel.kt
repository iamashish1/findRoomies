package com.example.findroomies.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findroomies.listeners.ToastMessageListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val auth:FirebaseAuth, private val toastListner:ToastMessageListener): ViewModel() {

     val emailText = ObservableField<String>("")
     val passwordText = ObservableField<String>("")
     val nameText= ObservableField<String>("")

    fun onSubmitClicked(isLogin:Boolean) {
        if(isLogin){
            val email = emailText.get()
            val password = passwordText.get()

            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                println("Email or password is empty")
                return
            }
            auth.signInWithEmailAndPassword(emailText.get()?:"", passwordText.get()?:"")
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        toastListner.showToast("Login Successful")
                    } else {
                        val exception = task.exception
                        toastListner.showToast("Login Failed: ${exception?.message}")

                    }
                }

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