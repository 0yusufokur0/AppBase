package com.resurrection.base.components.lifecycle.util

import androidx.lifecycle.LifecycleEventObserver
import com.resurrection.base.components.lifecycle.activity.ActivityComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

fun <T> LifecycleActivity.activityComponent(observer: LifecycleEventObserver? = null, instanceCreator: () -> T) = ActivityComponent(instanceCreator, observer)

fun <T> LifecycleFragment.fragmentComponent(observer: FragmentLifecycleEventObserver? = null, instanceCreator: () -> T) = FragmentComponent(instanceCreator, observer)