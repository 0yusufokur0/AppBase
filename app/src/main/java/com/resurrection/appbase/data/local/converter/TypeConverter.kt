package com.resurrection.appbase.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConverter {
    val gson = Gson()
    @TypeConverter
    inline fun <reified T> stringToModel(value: String) = Gson().fromJson(value, T::class.java)
    @TypeConverter
    inline fun <reified T> modelToString(value: T) = Gson().toJson(value)
}