package com.resurrection.base.components.lifecycle.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.resurrection.base.components.lifecycle.activity.ActivityComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentComponent

fun <T> AppCompatActivity.activityComponent(
    isSingle: Boolean = true,
    runBeforeGetValue: ((cachedValue: T?) -> Unit)? = null,
    observer: ActivityLifecycleEventObserver<T>? = null,
    instanceCreator: () -> T
) = ActivityComponent(isSingle, runBeforeGetValue, instanceCreator, observer)

fun <T> Fragment.fragmentComponent(
    isSingle: Boolean = true,
    runBeforeGetValue: ((cachedValue: T?) -> Unit)? = null,
    observer: FragmentLifecycleEventObserver<T>? = null,
    instanceCreator: () -> T
) = FragmentComponent(isSingle, runBeforeGetValue, instanceCreator, observer)