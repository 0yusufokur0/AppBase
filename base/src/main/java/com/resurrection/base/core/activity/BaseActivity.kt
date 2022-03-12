package com.resurrection.base.core.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.resurrection.base.component.*
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>
) : CoreActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }
    val  binding: VDB by lazy { DataBindingUtil.setContentView(this@BaseActivity, layoutRes) }

}