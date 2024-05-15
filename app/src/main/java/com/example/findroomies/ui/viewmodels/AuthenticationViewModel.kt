package com.example.findroomies.ui.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val auth:FirebaseAuth): ViewModel() {

     val emailText = ObservableField<String>("")
     val passwordText = ObservableField<String>("")
     val nameText= ObservableField<String>("")



    fun onSubmitClicked(isLogin:Boolean) {
        println("CLICKED")
        // Handle submit action
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
                        // Sign in success, update UI with the signed-in user's information
                       println("UUSER LOGIN SUCCESSFUL")

//                        val user = auth.currentUser
//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            baseContext,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                        updateUI(null)
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