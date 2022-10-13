package com.resurrection.base.components.lifecycle.activity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityComponent<T>(
    private val instanceCreator: () -> T,
    private val observer: LifecycleEventObserver? = null
) : LifecycleEventObserver, ReadOnlyProperty<LifecycleActivity, T> {

    private var cachedValue: T? = null
    private var cachedRef: LifecycleActivity? = null

    override fun getValue(thisRef: LifecycleActivity, property: KProperty<*>): T {
        cachedValue ?: run {
            cachedValue = instanceCreator.invoke()
            cachedRef = thisRef
            cachedRef?.lifecycle?.addObserver(this)
        }
        return cachedValue!!
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        observer?.onStateChanged(source, event)
        if (event == Lifecycle.Event.ON_DESTROY) {
            cachedRef?.lifecycle?.removeObserver(this)
            cachedValue = null
        }
    }

    fun isInitialized(): Boolean = cachedValue != null
}
