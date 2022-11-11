package com.resurrection.base.components.lifecycle.fragment

import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentComponent<T>(
    private val instanceCreator: () -> T,
    private val observer: FragmentLifecycleEventObserver? = null
) : FragmentLifecycleEventObserver, ReadOnlyProperty<CoreFragment, T> {

    private var cachedValue: T? = null
    private var cachedRef: CoreFragment? = null

    override fun getValue(thisRef: CoreFragment, property: KProperty<*>): T {
        cachedValue ?: run {
            cachedValue = instanceCreator.invoke()
            cachedRef = thisRef
            cachedRef?.addLifecycleObserver(this)
        }
        return cachedValue!!
    }

    override fun onStateChanged(owner: LifecycleOwner?, event: FragmentLifecycleEvent) {
        observer?.onStateChanged(owner, event)
        if (event == FragmentLifecycleEvent.ON_DESTROY_VIEW) {
            cachedRef?.removeLifecycleObserver(this)
            cachedValue = null
        }
    }

    fun isInitialized(): Boolean = cachedValue != null
}