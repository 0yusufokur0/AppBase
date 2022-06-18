package com.resurrection.base.components.data

import android.content.Context
import androidx.datastore.preferences.core.*
import com.resurrection.base.extensions.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreManager(context: Context) {
    private val manager = context.dataStore

    fun putInt(
        key: String,
        value: Int,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[intPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getInt(
        key: String,
        defValue: Int? = null,
        success: (Int?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[intPreferencesKey(key)] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putString(
        key: String,
        value: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[stringPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getString(
        key: String,
        defValue: String? = null,
        success: (String?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putBoolean(
        key: String,
        value: Boolean,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[booleanPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getBoolean(
        key: String,
        defValue: Boolean? = null,
        success: (Boolean?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putLong(
        key: String,
        value: Long,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[longPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getLong(
        key: String,
        defValue: Long? = null,
        success: (Long?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[longPreferencesKey(key)] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putFloat(
        key: String,
        value: Float,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[floatPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getFloat(
        key: String,
        defValue: Float? = null,
        success: (Float?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[floatPreferencesKey(key)] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putDouble(
        key: String,
        value: Double,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[doublePreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

    fun getDouble(
        key: String,
        defValue: Double? = null,
        success: (Double?) -> Unit,
        error: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        manager.data.map { preferences ->
            preferences[doublePreferencesKey(key)] ?: defValue
        }.catch {
            error.invoke()
        }.collect {
            success.invoke(it)
        }
    }

    fun putStringSet(
        key: String,
        value: Set<String>,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            manager.edit { preferences ->
                preferences[stringSetPreferencesKey(key)] = value
            }
            success.invoke()
        } catch (e: Exception) {
            error.invoke(e)
        }
    }

}