package com.example.findroomies.listeners

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {
    private val auth = FirebaseAuth.getInstance()

    fun addAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        println("STILL LISTENIONG")
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.removeAuthStateListener(listener)
    }
}
