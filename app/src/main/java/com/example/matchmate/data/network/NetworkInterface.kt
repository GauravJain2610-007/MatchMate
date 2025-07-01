package com.example.matchmate.data.network

import com.example.matchmate.data.network.models.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("https://randomuser.me/api/")
    suspend fun getUsersList( @Query("results") results: Int = 60, @Query("page") page: Int): Response<UserListResponse>
}