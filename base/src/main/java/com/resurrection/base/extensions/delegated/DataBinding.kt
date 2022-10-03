package com.resurrection.base.extensions.delegated

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.databinding.ViewDataBinding
import com.resurrection.base.components.lifecycle.activity.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.components.lifecycle.fragment.FragmentLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment


fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(
    @LayoutRes layoutRes: Int
): Lazy<VDB> = ActivityLifecycleAwareLazyComponent<VDB>(
    lifecycleActivity = this,
    instanceCreator = { setContentView(this, layoutRes) }
)

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding() = ActivityLifecycleAwareLazyComponent<VDB>(
    lifecycleActivity = this,
    instanceCreator = { setContentView(this, layoutRes) }
)

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding() = FragmentLifecycleAwareLazyComponent<VDB>(
    lifecycleFragment = this,
    instanceCreator = { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }
)

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding(
    @LayoutRes layoutRes: Int
) = FragmentLifecycleAwareLazyComponent<VDB>(
    lifecycleFragment = this,
    instanceCreator = { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }
)

fun <VDB : ViewDataBinding> View.dataBinding() = lazy { bind<VDB>(this) }