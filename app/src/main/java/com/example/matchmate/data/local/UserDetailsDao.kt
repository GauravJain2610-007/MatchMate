package com.example.matchmate.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matchmate.data.network.models.Id
import com.example.matchmate.data.network.models.UserDetails

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserDetails>)

    @Query("SELECT * FROM user_details")
    suspend fun getAllUsers(): List<UserDetails>

    @Query("SELECT * FROM user_details WHERE gender = 'female' LIMIT :limit ")
    fun getPagedUsers(limit:Int): PagingSource<Int, UserDetails>

    @Query("DELETE FROM user_details")
    suspend fun clearAll()


    @Query("Update user_details  set userLastAction =:userAction where id =:userId")
    suspend fun updateUserAction(userAction:String, userId: Id)


}