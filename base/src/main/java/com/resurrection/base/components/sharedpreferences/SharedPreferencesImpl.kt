package com.resurrection.base.components.sharedpreferences

import android.content.SharedPreferences as SPCore
import com.google.gson.Gson
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble

class SharedPreferencesImpl(
    private val sharedPreferences: SPCore
) : SharedPreferences {
    private val gson = Gson()
    override val editor: SPCore.Editor = sharedPreferences.edit()
    override fun contains(key: String): Boolean = sharedPreferences.contains(key)
    override fun size(): Int = sharedPreferences.all.size
    override fun isEmpty(): Boolean = sharedPreferences.all.isEmpty()
    override fun keySet(): Set<String> = sharedPreferences.all.keys
    override fun values(): Collection<Any?> = sharedPreferences.all.values
    override fun get(key: String): Any? = sharedPreferences.all[key]

    override fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }

    override fun clearAll() {
        editor.clear()
        editor.apply()
    }

    override fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    override fun getInt(key: String, defValue: Int): Int =
        sharedPreferences.getInt(key, defValue)

    override fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    override fun getString(key: String, defValue: String): String? =
        sharedPreferences.getString(key, defValue)

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defValue)

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    override fun getFloat(key: String, defValue: Float): Float =
        sharedPreferences.getFloat(key, defValue)

    override fun putLong(key: String, defValue: Long) {
        editor.putLong(key, defValue)
        editor.apply()
    }

    override fun getLong(key: String, defValue: Long): Long =
        sharedPreferences.getLong(key, defValue)

    override fun putDouble(key: String, value: Double) {
        editor.putLong(key, doubleToRawLongBits(value))
        editor.apply()
    }

    override fun getDouble(key: String, defValue: Double): Double =
        longBitsToDouble(getLong(key, doubleToRawLongBits(defValue)))

    override fun <Model> putObject(key: String, value: Model) {
        editor.putString(key, gson.toJson(value))
        editor.apply()
    }

    override fun <Model> getObject(key: String, clazz: Class<Model>): Model? =
        sharedPreferences.getString(key, null)?.let { gson.fromJson(it, clazz) }
}