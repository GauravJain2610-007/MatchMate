package com.example.matchmate.data.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("user_details")
data class UserDetails (
  @SerializedName("gender"     ) var gender     : String?     = null,
  @SerializedName("name"       ) var name       : Name?       = Name(),
  @SerializedName("location"   ) var location   : Location?   = Location(),
  @SerializedName("email"      ) var email      : String?     = null,
  @SerializedName("login"      ) var login      : Login?      = Login(),
  @SerializedName("dob"        ) var dob        : Dob?        = Dob(),
  @SerializedName("registered" ) var registered : Registered? = Registered(),
  @SerializedName("phone"      ) var phone      : String?     = null,
  @SerializedName("cell"       ) var cell       : String?     = null,
  @PrimaryKey @SerializedName("id"         ) var id         : Id        = Id(),
  @SerializedName("picture"    ) var picture    : Picture?    = Picture(),
  @SerializedName("nat"        ) var nat        : String?     = null,
  @SerializedName("matchPercentageColor"        ) var matchPercentage  : Int?     = null,
  @SerializedName("userLastAction"        ) var userLastAction  : String?     = null
)