package com.example.matchmate.di

import com.example.matchmate.data.local.UserDetailsDao
import com.example.matchmate.data.network.NetworkInterface
import com.example.matchmate.data.repository.MatchMateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryDI {
    @Provides
    @Singleton
    fun provideWeatherRepository(networkInterface: NetworkInterface, userDetailsDao: UserDetailsDao): MatchMateRepository {
        return MatchMateRepository(networkInterface, userDetailsDao)
    }
}