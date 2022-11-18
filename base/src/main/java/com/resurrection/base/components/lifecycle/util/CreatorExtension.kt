package com.resurrection.base.components.lifecycle.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.resurrection.base.components.lifecycle.activity.ActivityComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentComponent

fun <T> AppCompatActivity.activityComponent(
    observer: ActivityLifecycleEventObserver<T>? = null,
    instanceCreator: () -> T
) = ActivityComponent(instanceCreator, observer)

fun <T> Fragment.fragmentComponent(
    observer: FragmentLifecycleEventObserver<T>? = null,
    instanceCreator: () -> T
) = FragmentComponent(instanceCreator, observer)