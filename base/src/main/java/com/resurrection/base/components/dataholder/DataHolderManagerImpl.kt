package com.resurrection.base.components.dataholder

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import javax.inject.Inject

class DataHolderManagerImpl @Inject constructor() : DataHolderManager {

    override val manager = Bundle()

    override fun remove(key: String) = manager.remove(key)
    override fun contains(key: String) = manager.containsKey(key)
    override fun size() = manager.size()
    override fun isEmpty() = manager.isEmpty
    override fun keySet(): MutableSet<String> = manager.keySet()
    override fun clearAll() = manager.clear()
    override fun get(key: String) = manager.get(key)

    override fun putBundle(key: String, value: Bundle) =
        manager.putBundle(key, value)

    override fun getBundle(key: String): Bundle? =
        manager.getBundle(key)

    override fun putInt(key: String, value: Int) =
        manager.putInt(key, value)

    override fun getInt(key: String, defValue: Int?): Int? =
        handleGet(key, defValue, manager::getInt)

    override fun putString(key: String, value: String) =
        manager.putString(key, value)

    override fun getString(key: String, defValue: String?): String? =
        handleGet(key, defValue, manager::getString)

    override fun putBoolean(key: String, value: Boolean) =
        manager.putBoolean(key, value)

    override fun getBoolean(key: String, defValue: Boolean): Boolean? =
        handleGet(key, defValue, manager::getBoolean)

    override fun putFloat(key: String, value: Float) =
        manager.putFloat(key, value)

    override fun getFloat(key: String, defValue: Float?): Float? =
        handleGet(key, defValue, manager::getFloat)

    override fun putLong(key: String, value: Long) =
        manager.putLong(key, value)

    override fun getLong(key: String, defValue: Long?): Long? =
        handleGet(key, defValue, manager::getLong)

    override fun putDouble(key: String, value: Double) =
        manager.putDouble(key, value)

    override fun getDouble(key: String, defValue: Double?): Double? =
        handleGet(key, defValue, manager::getDouble)

    override fun <R : Serializable> putSerializable(key: String, value: R) =
        manager.putSerializable(key, value)

    override fun <R : Serializable> getSerializable(key: String) =
        handleGet(key, null, manager::getSerializable) as R?

    override fun <R : Parcelable> putParcelable(key: String, value: R) =
        manager.putParcelable(key, value)

    override fun <R : Parcelable> getParcelable(key: String) =
        handleGet(key, null, manager::getParcelable) as R?

    override fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>) =
        manager.putParcelableArrayList(key, value)

    override fun <T : Parcelable> getParcelableArrayList(key: String): ArrayList<T>? =
        handleGet(key, null, manager::getParcelableArrayList)

    override fun putIntegerArrayList(key: String, value: ArrayList<Int>) =
        manager.putIntegerArrayList(key, value)

    override fun getIntegerArrayList(key: String): ArrayList<Int>? =
        handleGet(key, null, manager::getIntegerArrayList)

    override fun putStringArrayList(key: String, value: ArrayList<String>) =
        manager.putStringArrayList(key, value)

    override fun getStringArrayList(key: String) = handleGet(key, null, manager::getStringArrayList)

    override fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>) =
        manager.putCharSequenceArrayList(key, value)

    override fun getCharSequenceArrayList(key: String): ArrayList<CharSequence>? =
        handleGet(key, null, manager::getCharSequenceArrayList)


    private fun <T> handleGet(key: String, defValue: T? = null, getValue: (String) -> T?): T? =
        if (contains(key)) getValue(key) else defValue
}
