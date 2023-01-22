package com.resurrection.base.components.dataholder

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 *
 * This class that holds key-value pairs in a [Bundle].
 * &nbsp;
 *
 * if you want more info see below page
 * @see <a href="https://developer.android.com/reference/android/os/Bundle.html"> Bundle </a>
 * */

interface DataHolder {

    val manager: Bundle

    /**
     * Removes any entry with the given key from this bundle.
     * @param key a String key
     * */
    fun remove(key: String)

    /**
     * If the given key is exist in the main bundle return true.
     * @return true if the key is in the bundle. Otherwise it returns false.
     * @param key a String key
     * */
    fun contains(key: String): Boolean

    /**
     * @return bundle entry size as Int
     * */
    fun size(): Int

    /**
     * @return true if the bundle entry size is 0. Otherwise it returns false.
     * */
    fun isEmpty(): Boolean

    /**
     * @return all bundle entry keys as Set<String>
     * */
    fun keySet(): Set<String>

    /**
     * Removes all entries in the main bundle.
     * */
    fun clearAll()

    /**
     * Get any value with the given key from main bundle.
     * @param key a String key
     * @return Any or null
     * */
    fun get(key: String): Any?

    /**
     * Insert bundle value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Bundle
     * */
    fun putBundle(key: String, value: Bundle)

    /**
     * Get bundle value with the given key from main bundle.
     * @param key a String key
     * @return a Bundle or null
     * */
    fun getBundle(key: String): Bundle?

    /**
     * Insert a int value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Int
     * */
    fun putInt(key: String, value: Int)

    /**
     * Get int value with the given key from main bundle.
     * @param key a String key
     * @return a Int
     * */
    fun getInt(key: String, defValue: Int): Int

    /**
     * Insert a string value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a String
     * */
    fun putString(key: String, value: String)

    /**
     * Get string value with the given key from main bundle.
     * @param key a String key
     * @return a String
     * */
    fun getString(key: String, defValue: String): String

    /**
     * Insert a boolean value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Boolean
     * */
    fun putBoolean(key: String, value: Boolean)

    /**
     * get boolean value with the given key from main bundle.
     * @param key a String key
     * @return a Boolean
     * */
    fun getBoolean(key: String, defValue: Boolean): Boolean

    /**
     * Insert a float value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Float
     * */
    fun putFloat(key: String, value: Float)

    /**
     * get float value with the given key from main bundle.
     * @param key a String key
     * @return a Float
     * */
    fun getFloat(key: String, defValue: Float): Float

    /**
     * Insert a long value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Long
     * */
    fun putLong(key: String, value: Long)

    /**
     * get long value with the given key from main bundle.
     * @param key a String key
     * @return a Long
     * */
    fun getLong(key: String, defValue: Long): Long

    /**
     * Insert a double value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Double
     * */
    fun putDouble(key: String, value: Double)

    /**
     * get double value with the given key from main bundle.
     * @param key a String key
     * @return a Double
     * */
    fun getDouble(key: String, defValue: Double): Double

    /**
     * Insert a serializable value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Serializable
     * */
    fun <R : Serializable> putSerializable(key: String, value: R)

    /**
     * get serializable value with the given key from main bundle.
     * @param key a String key
     * @return a Serializable or null
     * */
    fun <R : Serializable> getSerializable(key: String): R?

    /**
     * Insert a parcelable value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a Parcelable
     * */
    fun <R : Parcelable> putParcelable(key: String, value: R)

    /**
     * get parcelable value with the given key from main bundle.
     * @param key a String key
     * @return a Parcelable or null
     * */
    fun <R : Parcelable> getParcelable(key: String): R?

    /**
     * Insert a boolean array value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a ArrayList<out Parcelable>
     * */
    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>)

    /**
     * get parcelable arraylist value with the given key from main bundle.
     * @param key a String key
     * @return a ArrayList<Parcelable> or null
     * */
    fun <T : Parcelable> getParcelableArrayList(key: String): ArrayList<T>?

    /**
     * Insert a int array value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a ArrayList<Int>
     * */
    fun putIntegerArrayList(key: String, value: ArrayList<Int>)

    /**
     * get int arraylist value with the given key from main bundle.
     * @param key a String key
     * @return a ArrayList<Int> or null
     * */
    fun getIntegerArrayList(key: String): ArrayList<Int>?

    /**
     * Insert a string array value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a ArrayList<String>
     * */
    fun putStringArrayList(key: String, value: ArrayList<String>)

    /**
     * get string arraylist value with the given key from main bundle.
     * @param key a String key
     * @return a ArrayList<String> or null
     * */
    fun getStringArrayList(key: String): ArrayList<String>?

    /**
     * Insert a boolean array value into main bundle with the given key.
     * If key is exists in the main bundle, it value will be replaced.
     * @param key a String key
     * @param value a ArrayList<CharSequence>
     * */
    fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>)

    /**
     * get charsequence arraylist value with the given key from main bundle.
     * @param key a String key
     * @return a ArrayList<CharSequence> or null
     * */
    fun getCharSequenceArrayList(key: String): ArrayList<CharSequence>?
}
