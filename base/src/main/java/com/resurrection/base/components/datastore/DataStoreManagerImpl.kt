package com.resurrection.base.components.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import com.resurrection.base.extensions.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class DataStoreManagerImpl(context: Context):DataStoreManager {

    override val manager = context.dataStore

    override fun putInt(key: String, value: Int, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(intPreferencesKey(key), value, success, error)

    override fun getInt(key: String, defValue: Int, success: (Int) -> Unit, error: () -> Unit) =
        getUtil(intPreferencesKey(key), defValue, success, error)

    override fun putString(key: String, value: String, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(stringPreferencesKey(key), value, success, error)

    override fun getString(key: String, defValue: String, success: (String) -> Unit, error: () -> Unit) =
        getUtil(stringPreferencesKey(key), defValue, success, error)

    override fun putBoolean(key: String, value: Boolean, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(booleanPreferencesKey(key), value, success, error)

    override fun getBoolean(key: String, defValue: Boolean, success: (Boolean) -> Unit, error: () -> Unit) =
        getUtil(booleanPreferencesKey(key), defValue, success, error)

    override fun putFloat(key: String, value: Float, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(floatPreferencesKey(key), value, success, error)

    override fun getFloat(key: String, defValue: Float, success: (Float) -> Unit, error: () -> Unit) =
        getUtil(floatPreferencesKey(key), defValue, success, error)

    override fun putLong(key: String, value: Long, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(longPreferencesKey(key), value, success, error)

    override fun getLong(key: String, defValue: Long, success: (Long) -> Unit, error: () -> Unit) =
        getUtil(longPreferencesKey(key), defValue, success, error)

    override fun putDouble(key: String, value: Double, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(doublePreferencesKey(key), value, success, error)

    override fun getDouble(key: String, defValue: Double, success: (Double) -> Unit, error: () -> Unit) =
        getUtil(doublePreferencesKey(key), defValue, success, error)

    override fun putStringSet(key: String, value: Set<String>, success: () -> Unit, error: (IOException) -> Unit) =
        putUtil(stringSetPreferencesKey(key), value, success, error)

    override fun getStringSet(key: String, defValue: Set<String>, success: (Set<String>) -> Unit, error: () -> Unit) =
        getUtil(stringSetPreferencesKey(key), defValue, success, error)

    private fun <Value> putUtil(
        preferencesKey:Preferences.Key<Value>,
        value: Value,
        success: () -> Unit,
        error: (IOException) -> Unit,
    ) = CoroutineScope(Dispatchers.Default).launch {
        try {
            manager.edit { preferences ->
                preferences[preferencesKey] = value
            }
            success.invoke()
        } catch (e: IOException) {
            error.invoke(e)
        }
    }

    private fun <Value> getUtil(
        preferencesKey:Preferences.Key<Value>,
        defValue: Value,
        success: (Value) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.Default).launch {
        manager.data.map { preferences ->
            preferences[preferencesKey] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

}