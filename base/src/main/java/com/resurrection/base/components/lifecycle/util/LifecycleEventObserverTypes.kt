package com.resurrection.base.components.lifecycle.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.components.lifecycle.activity.ActivityComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentComponent

typealias ActivityLifecycleEventObserver<T> = ActivityComponent<T>.(
    LifecycleOwner,
    Lifecycle.Event,
    T?
) -> Unit
typealias FragmentLifecycleEventObserver<T> = FragmentComponent<T>.(
    LifecycleOwner,
    Lifecycle.Event,
    T?
) -> Unit