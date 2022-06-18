package com.resurrection.base.components.sharedpreferences

import android.content.SharedPreferences


/**
 *
 * This class that store key-value pairs in a [SharedPreferences].
 * &nbsp;
 *
 * if you want more info see below page
 * @see <a href="https://developer.android.com/reference/android/content/SharedPreferences"> SharedPreferences </a>
 * */
interface SharedPreferencesManager {

    val manager: SharedPreferences
    val editor: SharedPreferences.Editor

    /**
     * Removes any entry with the given key from this shared preferences.
     * @param key a String key
     * */
    fun remove(key: String)

    /**
     * If the given key is exist in the main shared preferences return true.
     * @return true if the key is in the shared preferences. Otherwise it returns false.
     * @param key a String key
     * */
    fun contains(key: String): Boolean

    /**
     * @return shared preferences entry size as Int
     * */
    fun size(): Int

    /**
     * @return true if the shared preferences entry size is 0. Otherwise it returns false.
     * */
    fun isEmpty(): Boolean

    /**
     * @return all shared preferences entry keys as List<String>
     * */
    fun keySet(): Set<String>

    /**
     * @return all shared preferences entry values as List<Any>
     * */
    fun values(): Collection<out Any?>

    /**
     * Removes all entries in the main shared preferences.
     * */
    fun clearAll()

    /**
     * Get any value with the given key from main shared preferences.
     * @param key a String key
     * @return Any or default value
     * */
    fun get(key: String): Any?

    /**
     * Insert a int value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a Int
     * */
    fun putInt(key: String, value: Int)

    /**
     * Get int value with the given key from main shared preferences.
     * @param key a String key
     * @return a Int or default value
     * */
    fun getInt(key: String, defValue: Int): Int

    /**
     * Insert a string value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a String
     * */
    fun putString(key: String, value: String)

    /**
     * Get string value with the given key from main shared preferences.
     * @param key a String key
     * @return a String or default value
     * */
    fun getString(key: String, defValue: String): String?

    /**
     * Insert a boolean value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a Boolean
     * */
    fun putBoolean(key: String, value: Boolean)

    /**
     * get boolean value with the given key from main shared preferences.
     * @param key a String key
     * @return a Boolean or default value
     * */
    fun getBoolean(key: String, defValue: Boolean): Boolean?

    /**
     * Insert a float value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a Float
     * */
    fun putFloat(key: String, value: Float)

    /**
     * get float value with the given key from main shared preferences.
     * @param key a String key
     * @return a Float or default value
     * */
    fun getFloat(key: String, defValue: Float): Float?

    /**
     * Insert a long value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param defValue a Long
     * */
    fun putLong(key: String, defValue: Long)

    /**
     * get long value with the given key from main shared preferences.
     * @param key a String key
     * @return a Long or default value
     * */
    fun getLong(key: String, defValue: Long): Long?

    /**
     * Insert a double value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a Double
     * */
    fun putDouble(key: String, value: Double)

    /**
     * get double value with the given key from main shared preferences.
     * @param key a String key
     * @return a Double or default value
     * */
    fun getDouble(key: String, defValue: Double): Double?

    /**
     * Insert a object value into main shared preferences with the given key.
     * If key is exists in the main shared preferences, it value will be replaced.
     * @param key a String key
     * @param value a Serializable
     * */
    fun <Model> putObject(key: String, value: Model)

    /**
     * get serializable value with the given key from main shared preferences.
     * @param key a String key
     * @return a Serializable or default value
     * */
    fun <Model> getObject(key: String, clazz: Class<Model>): Model?

}
