package com.example.matchmate.di

import com.example.matchmate.data.network.NetworkInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkDI {

    @Provides
    @Singleton
    fun provideRetrofitClient():NetworkInterface {
        return Retrofit
            .Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkInterface::class.java)
    }

}