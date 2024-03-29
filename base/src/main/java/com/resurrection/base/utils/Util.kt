package com.resurrection.base.utils

import android.util.Log
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/*fun Any.isValid(): Boolean {
    var isValid = true
    val fields = this.javaClass.declaredFields

    fields.forEachIndexed { i, field ->

        fields[i].isAccessible = true

        val value = fields[i].get(this)

        Log.w("Msg", "Value of Field " + fields[i].name + " is " + value)

        if (value == 0 || value == 0.0 || value == "" || value == null) {
            isValid = false
        }
    }
    return isValid
}*/

fun checkAreNotNull(vararg any:Any?): Boolean {
    any.forEach {
        it?: run {
            return false
        }
    }
    return true
}

fun Any?.isNotNull():Boolean{
    this?.let {
        return true
    }?: run {
        return false
    }
}fun Any?.isNull():Boolean{
    this?.let {
        return false
    }?: run {
        return true
    }
}

fun <T : Any> T.getPrivatePropertyOfJava(variableName: String): Any? {

    return javaClass.getDeclaredField(variableName).let { field ->
        field.isAccessible = true
        return@let field.get(this)
    }
}

fun <T : Any> T.setAndReturnPrivateProperty(variableName: String, data: Any): Any? {
    return javaClass.getDeclaredField(variableName).let { field ->
        field.isAccessible = true
        field.set(this, data)
        return@let field.get(this)
    }
}

inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
    T::class
        .declaredMemberFunctions
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.call(this, *args)

inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R? =
    T::class
        .memberProperties
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.get(this) as R?


inline fun <reified T> T.callPrivateFunctionWithIndex(index: Int, vararg args: Any?): Any? {
    return T::class
        .declaredMemberFunctions
        .toList()[index]
        .apply { isAccessible = true }
        .call(*args)
}

inline fun <reified T:Any> T.getPrivatePropertyValueByIndex(index: Int): Any? {
    return T::class
        .memberProperties
        .toList()[index]
        .apply { isAccessible = true }
        .get(this)
}

inline fun <reified  T> T.getFunctionIndexByName(name:String): Int? {
    val functionList =  T::class.declaredMemberFunctions
    functionList.forEachIndexed { index, kFunction ->
        if (kFunction.name == name){
            return index
        }
    }
    return null
}

inline fun <reified  T:Any> T.getPropertyIndexByName(name:String): Int? {

    val propertyList =  T::class.memberProperties
    propertyList.forEachIndexed { index, kProperty ->
        if (kProperty.name == name){
            return index
        }
    }
    return null
}