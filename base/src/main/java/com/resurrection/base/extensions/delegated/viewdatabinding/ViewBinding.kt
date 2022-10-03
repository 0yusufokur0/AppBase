package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.activity.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment


fun <VB : ViewBinding> LifecycleActivity.viewBinding(
    initializer: (LayoutInflater) -> VB
) = ActivityLifecycleAwareLazyComponent(
    lifecycleActivity = this,
    instanceCreator = { initializer.invoke(layoutInflater) }
)


fun <VB : ViewBinding> LifecycleFragment.viewBinding(
    initializer: (LayoutInflater) -> VB
) = FragmentLifecycleAwareLazyComponent(
    lifecycleFragment = this,
    instanceCreator = { initializer.invoke(layoutInflater) }
)

fun <VB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }