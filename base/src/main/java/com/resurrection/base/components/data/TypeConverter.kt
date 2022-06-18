package com.resurrection.base.components.data

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

class TypeConverter {

    private val gson = Gson()

    fun <T> toJson(obj: T): String {
        return gson.toJson(obj)
    }

    fun <T> fromJson(json: String): T {
        return gson.fromJson(json,getType())
    }

    fun <T> getType(): Class<T> {
        val asd = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return asd
    }

}