package com.resurrection.base.extensions.delegated.viewdatabinding.activity

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.activity.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(@LayoutRes layoutRes: Int): Lazy<VDB> =
    ActivityLifecycleAwareLazyComponent<VDB>(
        lifecycleActivity = this,
        instanceCreator = { DataBindingUtil.setContentView(this, layoutRes) }
    )

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(): Lazy<VDB> =
    ActivityLifecycleAwareLazyComponent<VDB>(
        lifecycleActivity = this,
        instanceCreator = { DataBindingUtil.setContentView(this, layoutRes) }
    )

fun <VB : ViewBinding> LifecycleActivity.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    ActivityLifecycleAwareLazyComponent<VB>(
        lifecycleActivity = this,
        instanceCreator = { initializer.invoke(layoutInflater) }
    )