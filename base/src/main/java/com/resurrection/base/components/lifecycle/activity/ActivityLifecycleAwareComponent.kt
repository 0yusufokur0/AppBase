package com.resurrection.base.components.lifecycle.activity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.core.activity.LifecycleActivity

open class ActivityLifecycleAwareComponent<T>(
    private val lifecycleActivity: LifecycleActivity,
    private val instanceCreator: () -> T,
    private val observer: LifecycleEventObserver? = null
) : LifecycleEventObserver {

    protected var cached: T? = null

    open val value: T
        get() {
            cached ?: run {
                cached = instanceCreator.invoke()
                lifecycleActivity.lifecycle.addObserver(this)
            }
            return cached!!
        }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        observer?.onStateChanged(source, event)
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleActivity.lifecycle.removeObserver(this)
            cached = null
        }
    }
}