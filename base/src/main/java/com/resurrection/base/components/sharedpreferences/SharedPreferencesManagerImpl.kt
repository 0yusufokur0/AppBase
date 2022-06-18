package com.resurrection.base.components.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.resurrection.base.components.data.TypeConverter
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble
import javax.inject.Inject


class SharedPreferencesManagerImpl @Inject constructor(
    private val typeConverter: TypeConverter,
    context: Context
) : SharedPreferencesManager {

    override val manager: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    override val editor: SharedPreferences.Editor = manager.edit()
    override fun contains(key: String): Boolean = manager.contains(key)
    override fun size(): Int = manager.all.size
    override fun isEmpty(): Boolean = manager.all.isEmpty()
    override fun keySet(): Set<String> = manager.all.keys
    override fun values(): Collection<Any?> = manager.all.values
    override fun get(key: String): Any? = manager.all[key]

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
        manager.getInt(key, defValue)

    override fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    override fun getString(key: String, defValue: String): String? =
        manager.getString(key, defValue)

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean =
        manager.getBoolean(key, defValue)

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    override fun getFloat(key: String, defValue: Float): Float =
        manager.getFloat(key, defValue)

    override fun putLong(key: String, defValue: Long) {
        editor.putLong(key, defValue)
        editor.apply()
    }

    override fun getLong(key: String, defValue: Long): Long =
        manager.getLong(key, defValue)

    override fun putDouble(key: String, value: Double) {
        editor.putLong(key, doubleToRawLongBits(value))
        editor.apply()
    }

    override fun getDouble(key: String, defValue: Double): Double =
        getLong(key, doubleToRawLongBits(defValue)).let { longBitsToDouble(it) }

    override fun <Model> putObject(key: String, value: Model) {
        editor.putString(key, typeConverter.toJson(value))
        editor.apply()
    }

    override fun <Model> getObject(key: String): Model? =
        manager.getString(key, null)?.let { typeConverter.fromJson(it) }


}