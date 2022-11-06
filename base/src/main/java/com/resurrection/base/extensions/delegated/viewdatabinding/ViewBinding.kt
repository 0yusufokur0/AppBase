package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.core.fragment.CoreFragment

fun <VB : ViewBinding> CoreActivity.viewBinding(initializer: (LayoutInflater) -> VB) =
    activityComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> CoreFragment.viewBinding(initializer: (LayoutInflater) -> VB) =
    fragmentComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }