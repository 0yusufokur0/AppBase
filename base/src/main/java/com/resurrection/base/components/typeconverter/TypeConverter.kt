package com.resurrection.base.components.typeconverter

import com.google.gson.Gson

class TypeConverter {

    private val gson = Gson()

    fun <T> toJson(obj: T): String = gson.toJson(obj)

    fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson(json, clazz)

}