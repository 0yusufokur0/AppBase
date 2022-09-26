package com.resurrection.base.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.CoreFragment

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(): Lazy<VDB> {

    return ActivityLifecycleAwareLazyComponent<VDB>(
        lifecycleActivity = this,
        instanceCreator = {
            DataBindingUtil.setContentView(this, layoutRes)
        }, observer =  { source, event ->

        }
    )


}

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(@LayoutRes layoutRes: Int): Lazy<VDB> =
    lazy<VDB> { DataBindingUtil.setContentView(this, layoutRes) }

fun <VDB : ViewDataBinding> CoreFragment.dataBinding(): Lazy<VDB> =
    lazy<VDB> { DataBindingUtil.setContentView(requireActivity(), layoutRes) }

fun <VDB : ViewDataBinding> Fragment.dataBinding(@LayoutRes layoutRes: Int): Lazy<VDB> =
    lazy<VDB> { DataBindingUtil.setContentView(requireActivity(), layoutRes) }

fun <VDB : ViewDataBinding> View.dataBinding(): Lazy<VDB> =
    lazy<VDB> { DataBindingUtil.setContentView(context as Activity, this.id) }

fun <VDB : ViewBinding> LifecycleActivity.viewBinding(initializer: (LayoutInflater) -> VDB): Lazy<VDB> =
    lazy { initializer.invoke(layoutInflater) }

fun <VDB : ViewBinding> Fragment.viewBinding(initializer: (LayoutInflater) -> VDB): Lazy<VDB> =
    lazy { initializer.invoke(layoutInflater) }

fun <VDB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VDB): Lazy<VDB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }






