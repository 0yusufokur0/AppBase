package com.resurrection.base.components.datastore

import kotlinx.coroutines.Job
import java.io.IOException

interface DataStore {

    fun putInt(
        key: String,
        value: Int,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getInt(key: String, defValue: Int, success: (Int) -> Unit, error: () -> Unit): Job

    fun putString(
        key: String,
        value: String,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getString(key: String, defValue: String, success: (String) -> Unit, error: () -> Unit): Job

    fun putBoolean(
        key: String,
        value: Boolean,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getBoolean(key: String, defValue: Boolean, success: (Boolean) -> Unit, error: () -> Unit): Job

    fun putFloat(
        key: String,
        value: Float,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getFloat(key: String, defValue: Float, success: (Float) -> Unit, error: () -> Unit): Job

    fun putLong(
        key: String,
        value: Long,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getLong(key: String, defValue: Long, success: (Long) -> Unit, error: () -> Unit): Job

    fun putDouble(
        key: String,
        value: Double,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getDouble(key: String, defValue: Double, success: (Double) -> Unit, error: () -> Unit): Job

    fun putStringSet(
        key: String,
        value: Set<String>,
        success: () -> Unit = { },
        error: (IOException) -> Unit = { }
    ): Job
    fun getStringSet(
        key: String,
        defValue: Set<String>,
        success: (Set<String>) -> Unit,
        error: () -> Unit
    ): Job
}