package com.example.matchmate.di

import android.content.Context
import com.example.matchmate.data.local.MatchMateLocalDB
import com.example.matchmate.data.local.UserDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseDi {

    @Provides
    fun provideUserDao(appDatabase: MatchMateLocalDB): UserDetailsDao {
        return appDatabase.userProfileDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MatchMateLocalDB {
        return MatchMateLocalDB.getInstance(appContext)
    }
}