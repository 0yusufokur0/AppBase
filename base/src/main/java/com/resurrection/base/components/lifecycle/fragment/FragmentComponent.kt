package com.resurrection.base.components.lifecycle.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.components.lifecycle.util.FragmentLifecycleEventObserver
import com.resurrection.base.utils.isNotNull
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentComponent<T>(
    private val instanceCreator: () -> T,
    private val observer: FragmentLifecycleEventObserver<T>? = null
) : LifecycleEventObserver, ReadOnlyProperty<Fragment, T> {

    private var cachedValue: T? = null
    private var cachedRef: Fragment? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        init(thisRef).cachedValue!!

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        observer?.invoke(this, source, event, cachedValue)
        if (event == Lifecycle.Event.ON_DESTROY) {
            cachedRef?.viewLifecycleOwner?.lifecycle?.removeObserver(this)
            cachedValue = null
        }
    }

    fun initNow(thisRef: Fragment) = init(thisRef)

    private fun init(thisRef: Fragment) = apply {
        cachedValue ?: run {
            cachedValue = instanceCreator.invoke()
        }
        cachedRef ?: run {
            cachedRef = thisRef
            cachedRef?.viewLifecycleOwner?.lifecycle?.addObserver(this)
        }
    }

    fun isInitialized(): Boolean = cachedValue.isNotNull()
}