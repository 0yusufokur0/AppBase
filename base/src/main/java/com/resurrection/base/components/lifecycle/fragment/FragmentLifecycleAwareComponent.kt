package com.resurrection.base.components.lifecycle.fragment

import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

open class FragmentLifecycleAwareComponent<T>(
    private val lifecycleFragment: LifecycleFragment,
    private val instanceCreator: () -> T,
    private val observer: FragmentLifecycleEventObserver? = null
) : FragmentLifecycleEventObserver {

    protected var cached: T? = null

    open val value: T
        get() {
            cached ?: run {
                cached = instanceCreator.invoke()
                lifecycleFragment.addLifecycleObserver(this)
            }
            return cached!!
        }

    override fun onStateChanged(owner: LifecycleOwner?, event: FragmentLifecycleEvent) {
        observer?.onStateChanged(owner, event)
        if (event == FragmentLifecycleEvent.ON_DESTROY_VIEW) {
            lifecycleFragment.removeLifecycleObserver(this)
            cached = null
        }
    }
}