package com.resurrection.base.extensions.delegated.viewdatabinding.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.fragment.FragmentLifecycleAwareLazyComponent
import com.resurrection.base.core.fragment.LifecycleFragment

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding(): Lazy<VDB> =
    FragmentLifecycleAwareLazyComponent(
        lifecycleFragment = this,
        instanceCreator = { DataBindingUtil.setContentView(requireActivity(), layoutRes) },

        )

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding(@LayoutRes layoutRes: Int): Lazy<VDB> =
    FragmentLifecycleAwareLazyComponent(
        lifecycleFragment = this,
        instanceCreator = {
            DataBindingUtil.inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false)
        }
    )

fun <VB : ViewBinding> LifecycleFragment.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    FragmentLifecycleAwareLazyComponent(
        lifecycleFragment = this,
        instanceCreator = {
            initializer.invoke(layoutInflater)
        }
    )