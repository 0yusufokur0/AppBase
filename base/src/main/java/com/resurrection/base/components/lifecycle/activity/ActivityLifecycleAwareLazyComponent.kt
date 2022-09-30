package com.resurrection.base.components.lifecycle.activity

import androidx.lifecycle.LifecycleEventObserver
import com.resurrection.base.core.activity.LifecycleActivity

class ActivityLifecycleAwareLazyComponent<T>(
    lifecycleActivity: LifecycleActivity,
    instanceCreator: () -> T,
    observer: LifecycleEventObserver? = null
) : ActivityLifecycleAwareComponent<T>(lifecycleActivity, instanceCreator, observer), Lazy<T> {

    override fun isInitialized(): Boolean = cached != null

}