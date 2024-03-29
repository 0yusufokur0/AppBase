package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes val layoutRes: Int, private val viewModelClass: Class<VM>
) : LifecycleFragment(layoutRes) {

    private var _binding: VDB? = null
    val binding get() = _binding!!
    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }

    abstract override fun init(view: View, savedInstanceState: Bundle?)

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return _binding!!.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callOnViewCreatedSuper(view, savedInstanceState)
        init(view, savedInstanceState)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}