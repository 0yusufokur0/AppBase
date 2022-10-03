package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes layoutRes: Int, viewModelClass: Class<VM>
) : LifecycleFragment(layoutRes) {

    protected val binding by dataBinding<VDB>()
    protected val viewModel by viewModel(viewModelClass)

    abstract override fun init(view: View, savedInstanceState: Bundle?)

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callOnViewCreatedSuper(view, savedInstanceState)
        init(view, savedInstanceState)
    }

}