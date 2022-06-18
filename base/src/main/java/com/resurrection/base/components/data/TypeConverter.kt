package com.resurrection.base.components.data

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

class TypeConverter {

    private val gson = Gson()

    fun <T> toJson(obj: T): String = gson.toJson(obj)

    fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson(json, clazz)

}