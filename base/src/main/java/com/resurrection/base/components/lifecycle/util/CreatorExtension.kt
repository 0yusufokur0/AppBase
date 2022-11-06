package com.resurrection.base.components.lifecycle.util

import androidx.lifecycle.LifecycleEventObserver
import com.resurrection.base.components.lifecycle.activity.ActivityComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentComponent
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

fun <T> CoreActivity.activityComponent(observer: LifecycleEventObserver? = null, instanceCreator: () -> T) = ActivityComponent(instanceCreator, observer)

fun <T> CoreFragment.fragmentComponent(observer: FragmentLifecycleEventObserver? = null, instanceCreator: () -> T) = FragmentComponent(instanceCreator, observer)