package com.example.matchmate.data.network.models

import com.google.gson.annotations.SerializedName


data class Street (

  @SerializedName("number" ) var number : String?    = null,
  @SerializedName("name"   ) var name   : String? = null

)