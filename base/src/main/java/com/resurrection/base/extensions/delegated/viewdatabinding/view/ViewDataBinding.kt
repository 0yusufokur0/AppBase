package com.resurrection.base.extensions.delegated.viewdatabinding.view

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

fun <VDB : ViewDataBinding> View.dataBinding() = lazy { DataBindingUtil.bind<VDB>(this) }

fun <VB : ViewBinding> View.viewBinding(initializer: (LayoutInflater) -> VB): Lazy<VB> =
    lazy { initializer.invoke(LayoutInflater.from(context)) }