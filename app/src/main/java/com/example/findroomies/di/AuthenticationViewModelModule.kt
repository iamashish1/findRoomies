package com.example.findroomies.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AuthenticationViewModelModule {
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return  FirebaseAuth.getInstance()
    }

}