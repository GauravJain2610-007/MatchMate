package com.example.matchmate.data.local

import androidx.room.TypeConverter
import com.example.matchmate.data.network.models.Dob
import com.example.matchmate.data.network.models.Id
import com.example.matchmate.data.network.models.Location
import com.example.matchmate.data.network.models.Login
import com.example.matchmate.data.network.models.Name
import com.example.matchmate.data.network.models.Picture
import com.example.matchmate.data.network.models.Registered
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromName(name: Name?): String? {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toName(json: String?): Name? {
        return gson.fromJson(json, object : TypeToken<Name>() {}.type)
    }

    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(json: String?): Location? {
        return gson.fromJson(json, object : TypeToken<Location>() {}.type)
    }

    @TypeConverter
    fun fromLogin(login: Login?): String? {
        return gson.toJson(login)
    }

    @TypeConverter
    fun toLogin(json: String?): Login? {
        return gson.fromJson(json, object : TypeToken<Login>() {}.type)
    }

    @TypeConverter
    fun fromDob(dob: Dob?): String? {
        return gson.toJson(dob)
    }

    @TypeConverter
    fun toDob(json: String?): Dob? {
        return gson.fromJson(json, object : TypeToken<Dob>() {}.type)
    }

    @TypeConverter
    fun fromIdInfo(id: Id?): String? {
        return gson.toJson(id)
    }

    @TypeConverter
    fun toIdInfo(json: String?): Id? {
        return gson.fromJson(json, object : TypeToken<Id>() {}.type)
    }

    @TypeConverter
    fun fromPicture(picture: Picture?): String? {
        return gson.toJson(picture)
    }

    @TypeConverter
    fun toPicture(json: String?): Picture? {
        return gson.fromJson(json, object : TypeToken<Picture>() {}.type)
    }

    @TypeConverter
    fun fromRegistered(picture: Registered?): String? {
        return gson.toJson(picture)
    }

    @TypeConverter
    fun toRegistered(json: String?): Registered? {
        return gson.fromJson(json, object : TypeToken<Registered>() {}.type)
    }
}
