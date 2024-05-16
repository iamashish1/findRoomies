package com.example.findroomies.di

import com.example.findroomies.listeners.ToastMessageListener
import com.example.findroomies.ui.activities.SplashActivity
import com.example.findroomies.ui.fragments.SignInFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AuthenticationViewModelModule {
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return  FirebaseAuth.getInstance()
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class ToastListenerModule {
    @Binds
    abstract fun bindToastMessageListener(activity: SplashActivity): ToastMessageListener
}

