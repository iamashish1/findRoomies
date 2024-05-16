package com.example.findroomies.ui.viewmodels

import android.R.attr
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findroomies.listeners.ToastMessageListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val auth:FirebaseAuth, private val toastListner:ToastMessageListener): ViewModel() {

     val emailText = ObservableField<String>("")
     val passwordText = ObservableField<String>("")
     val nameText= ObservableField<String>("")

    private val navigateToHomeActivity = MutableLiveData<Boolean>(false)

    // Method to expose LiveData to the activity/fragment
    fun getNavigateToHomeActivity(): LiveData<Boolean>? {
        return navigateToHomeActivity
    }

    // Method to trigger navigation
    fun setNavigateValue(value:Boolean) {
        navigateToHomeActivity.value = value
    }

    fun onSubmitClicked(isLogin:Boolean) {

        val email = emailText.get()
        val password = passwordText.get()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            println("Email or password is empty")
            return
        }
        if(isLogin){

            auth.signInWithEmailAndPassword(emailText.get()?:"", passwordText.get()?:"")
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        setNavigateValue(true)
                    } else {
                        val exception = task.exception

                    }
                }

        }else{
            val name = nameText.get()?:""
            auth.createUserWithEmailAndPassword(emailText.get()?:"", passwordText.get()?:"")
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        setNavigateValue(true)
                        val userId =
                            auth.currentUser!!.uid // Get user ID after successful registration

                        saveUserDataToFirestore(email,name, userId)
                        println("Registered ")
                    } else {
                        val exception = task.exception
                        println(exception?.message)

                    }
                }



        }
    }

    private fun saveUserDataToFirestore(email: String, name: String, userId: String) {
        // Create a user document with relevant data
        val user: MutableMap<String, Any> = HashMap()
        user["email"] = email
        user["name"] = name
        user["userId"] = userId // Add user ID to the document
        // You can add more user data as needed
        val db = FirebaseFirestore.getInstance()

        db.collection("Users").document(userId)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    fun isValidInput(): Boolean {
        val inputEm = emailText.get() ?: return false
        val inputPass = passwordText.get() ?: return false

        return inputEm.isNotEmpty() && inputPass.isNotEmpty()
    }

}