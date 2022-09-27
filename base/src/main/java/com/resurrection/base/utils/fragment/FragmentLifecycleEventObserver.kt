package com.resurrection.base.utils.fragment

import androidx.lifecycle.LifecycleOwner

interface FragmentLifecycleEventObserver {
    fun onStateChanged(owner: LifecycleOwner,event: FragmentLifecycleEvent)
}