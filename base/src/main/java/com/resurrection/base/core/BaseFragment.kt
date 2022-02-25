package com.resurrection.base.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.resurrection.base.component.*
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import com.resurrection.base.util.Status
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes val resLayoutId: Int, private val viewModelClass: Class<VM>
) : Fragment() {

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var networkManager: NetworkManager
    @Inject lateinit var securityManager: SecurityManager

    private var _binding: VDB? = null
    val binding get() = _binding!!
    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.fragmentOnCreate()
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, resLayoutId, container, false)
        return _binding!!.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.fragmentName = this.javaClass.simpleName
        appState.isNetworkAvailable = networkManager.checkNetworkAvailable()
        init(savedInstanceState)
    }

    fun <T> LiveData<Resource<T>>.observeData(
        success: (T?) -> Unit,
        loading: (() -> Unit)? = null,
        error: (() -> Unit)? = null
    ) {
        this.observe(this@BaseFragment) { data ->
            when (data.status) {
                Status.SUCCESS -> success.invoke(data.data)
                Status.LOADING -> loading?.invoke()
                Status.ERROR -> error?.invoke()
                else ->  ThrowableError("${data.data} fetch error")
            }
        }
    }

    // region LifeCycle
    override fun onStart() {
        super.onStart()
        logger.fragmentOnStart()
    }

    override fun onResume() {
        super.onResume()
        logger.fragmentOnResume()
    }

    override fun onPause() {
        super.onPause()
        logger.fragmentOnPause()
    }

    override fun onStop() {
        super.onStop()
        logger.fragmentOnStop()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        logger.fragmentOnDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.fragmentOnDestroy()
    }
    // endregion
}