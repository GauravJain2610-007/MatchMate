package com.example.matchmate.data.network.models

import com.google.gson.annotations.SerializedName


data class UserListResponse (
    @SerializedName("results" ) var results : ArrayList<UserDetails> = arrayListOf(),
    @SerializedName("info"    ) var info    : Info?              = Info()
)