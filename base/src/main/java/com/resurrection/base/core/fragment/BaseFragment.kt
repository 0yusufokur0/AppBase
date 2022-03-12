package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.resurrection.base.component.*
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import com.resurrection.base.util.Status
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes val resLayoutId: Int, private val viewModelClass: Class<VM>
) : LifecycleFragment() {

    private var _binding: VDB? = null
    val binding get() = _binding!!
    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, resLayoutId, container, false)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}