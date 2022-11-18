package com.resurrection.base.extensions.delegated.viewdatabinding

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.resurrection.base.components.lifecycle.util.activityComponent
import com.resurrection.base.components.lifecycle.util.fragmentComponent

fun <VB : ViewBinding> AppCompatActivity.viewBinding(initializer: (LayoutInflater) -> VB) =
    activityComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> Fragment.viewBinding(initializer: (LayoutInflater) -> VB) =
    fragmentComponent { initializer.invoke(layoutInflater) }

fun <VB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }