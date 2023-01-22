package com.resurrection.base.components.lifecycle.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.components.lifecycle.util.ActivityLifecycleEventObserver
import com.resurrection.base.utils.isNotNull
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityComponent<T>(
    private val isSingle: Boolean,
    private val runBeforeGetValue: ((cachedValue: T?) -> Unit)? = null,
    private val instanceCreator: () -> T,
    private val observer: ActivityLifecycleEventObserver<T>? = null
) : LifecycleEventObserver, ReadOnlyProperty<AppCompatActivity, T> {

    private var cachedValue: T? = null
    private var cachedRef: AppCompatActivity? = null

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        runBeforeGetValue?.invoke(cachedValue)
        return initNow(thisRef).cachedValue!!
    }

    override fun onStateChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        observer?.invoke(this, owner, event, cachedValue)
        if (event == Lifecycle.Event.ON_DESTROY) {
            cachedRef?.lifecycle?.removeObserver(this)
            cachedValue = null
        }
    }

    fun initNow(thisRef: AppCompatActivity): ActivityComponent<T> {
        if (!isSingle) cachedValue = instanceCreator.invoke()
        else {
            cachedValue ?: run {
                cachedValue = instanceCreator.invoke()
            }
        }
        cachedRef ?: run {
            cachedRef = thisRef
            cachedRef?.lifecycle?.addObserver(this)
        }
        return this
    }

    fun isInitialized(): Boolean = cachedValue.isNotNull()
}
