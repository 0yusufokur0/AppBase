package com.resurrection.base.components.lifecycle.fragment

import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

class FragmentLifecycleAwareLazyComponent<T>(
    lifecycleFragment: LifecycleFragment,
    instanceCreator: () -> T,
    observer: FragmentLifecycleEventObserver? = null
) : FragmentLifecycleAwareComponent<T>(lifecycleFragment, instanceCreator, observer), Lazy<T> {

    override fun isInitialized(): Boolean = cached != null

}