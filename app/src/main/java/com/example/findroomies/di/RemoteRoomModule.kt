package com.example.findroomies.di

import com.example.findroomies.data.repository.RoomRepository
import com.example.findroomies.data.repository.RoomRepositoryImpl
import com.example.findroomies.listeners.ToastMessageListener
import com.example.findroomies.ui.activities.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


//@Module
//@InstallIn(ActivityComponent::class)
//abstract class RoomModule {
//    @Binds
//    abstract fun bindRoomInterfaceToImpl(roomImpl: RoomRepositoryImpl): RoomRepository
//}
