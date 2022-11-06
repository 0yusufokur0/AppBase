package com.resurrection.base.core.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel

abstract class BaseActivity<VDB:ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes layoutRes: Int,
    viewModelClass: Class<VM>
) : LifecycleActivity(layoutRes) {

    protected val binding by dataBinding<VDB>(layoutRes)
    protected val viewModel by viewModel(viewModelClass)

    abstract override fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init(savedInstanceState)
    }

}