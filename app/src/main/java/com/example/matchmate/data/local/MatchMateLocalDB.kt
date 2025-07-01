package com.example.matchmate.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.matchmate.data.network.models.UserDetails
import javax.inject.Singleton


@Singleton
@Database(entities = [UserDetails::class], version = 1)
@TypeConverters(UserTypeConverters::class)
abstract class MatchMateLocalDB : RoomDatabase()  {

    abstract fun userProfileDao(): UserDetailsDao

    companion object {
        @Volatile private var INSTANCE: MatchMateLocalDB? = null

        fun getInstance(context: Context): MatchMateLocalDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                        context.applicationContext,
                        MatchMateLocalDB::class.java,
                        "MatchMateLocalDB.db"
                    ).fallbackToDestructiveMigration(false)
                .build()
    }
}