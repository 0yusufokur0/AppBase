package com.resurrection.base.core.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>
) : LifecycleActivity(layoutRes) {

    private val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }
    val binding: VDB by lazy { DataBindingUtil.setContentView(this@BaseActivity, layoutRes) }

    abstract override fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        callOnCreateSuper(savedInstanceState)
        setContentView(binding.root)
        init(savedInstanceState)
    }

}