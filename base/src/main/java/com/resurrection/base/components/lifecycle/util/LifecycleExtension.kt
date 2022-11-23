package com.resurrection.base.components.lifecycle.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

fun Lifecycle.addObserver(listener: (source: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    addObserver(
        LifecycleEventObserver { source, event ->
            listener.invoke(source, event)
        }
    )
}