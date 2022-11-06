package com.resurrection.base.components.lifecycle.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

fun Lifecycle.addObserver(listener:(source:LifecycleOwner,event:Lifecycle.Event)->Unit){
    addObserver(LifecycleEventObserver { source, event ->
       listener.invoke(source, event)
    })
}

fun CoreFragment.addObserver(listener:(owner: LifecycleOwner?, event: FragmentLifecycleEvent)->Unit){
    addLifecycleObserver(object : FragmentLifecycleEventObserver {
        override fun onStateChanged(owner: LifecycleOwner?, event: FragmentLifecycleEvent) {
            listener.invoke(owner, event)
        }
    })
}