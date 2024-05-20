package com.example.findroomies.ui.viewmodels



import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findroomies.data.model.FirebaseUserModel
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _profileData = MutableLiveData<FirebaseUserModel?>()
    val profileData: LiveData<FirebaseUserModel?>
        get() = _profileData

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            try {
                val docRef = db.collection("Users").document(auth.currentUser?.uid ?: "")
                val document = docRef.get().await()
                if (document.exists()) {
                    val userData = document.toObject(FirebaseUserModel::class.java)
                    _profileData.postValue(userData)
                } else {
                    _profileData.postValue(null)
                }
            } catch (e: Exception) {
                _profileData.postValue(null)
                throw e
            }
        }
    }


}