package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment

fun <VB : ViewBinding> LifecycleActivity.viewBinding(initializer: (LayoutInflater) -> VB) =
    activityComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> LifecycleFragment.viewBinding(initializer: (LayoutInflater) -> VB) =
    fragmentComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }