package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil.*
import androidx.databinding.ViewDataBinding
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.LifecycleActivity
import com.resurrection.base.core.fragment.LifecycleFragment

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding(@LayoutRes layoutRes: Int) =
    activityComponent<VDB> { setContentView(this, layoutRes) }

fun <VDB : ViewDataBinding> LifecycleActivity.dataBinding() =
    activityComponent<VDB> { setContentView(this, layoutRes) }

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding() =
    fragmentComponent<VDB> { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }

fun <VDB : ViewDataBinding> LifecycleFragment.dataBinding(@LayoutRes layoutRes: Int) =
    fragmentComponent<VDB> { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }

fun <VDB : ViewDataBinding> View.dataBinding() = lazy { bind<VDB>(this) }