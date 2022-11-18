package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.*
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.core.fragment.CoreFragment

fun <VDB : ViewDataBinding> CoreActivity.dataBinding() =
    activityComponent<VDB> { setContentView(this, layoutRes) }

fun <VDB : ViewDataBinding> AppCompatActivity.dataBinding(@LayoutRes layoutRes: Int) =
    activityComponent<VDB> { setContentView(this, layoutRes) }

fun <VDB : ViewDataBinding> CoreFragment.dataBinding() =
    fragmentComponent<VDB> { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }

fun <VDB : ViewDataBinding> Fragment.dataBinding(@LayoutRes layoutRes: Int) =
    fragmentComponent<VDB> { inflate(layoutInflater, layoutRes, view?.parent as ViewGroup?, false) }

fun <VDB : ViewDataBinding> View.dataBinding() = lazy { bind<VDB>(this) }