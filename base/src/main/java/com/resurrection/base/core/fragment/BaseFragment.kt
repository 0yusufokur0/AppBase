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
) : CoreFragment() {

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var loggerManager: LoggerManager
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var networkManager: NetworkManager
    @Inject lateinit var securityManager: SecurityManager

    private var _binding: VDB? = null
    val binding get() = _binding!!
    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }

    abstract fun init(savedInstanceState: Bundle?)

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

    override fun onStateChanged(event: FragmentLifecycleEvent) {
        when(event){
            FragmentLifecycleEvent.ON_CREATE -> {
                loggerManager.fragmentOnCreate()
            }
            FragmentLifecycleEvent.ON_CREATE_VIEW -> {
            }
            FragmentLifecycleEvent.ON_VIEW_CREATED -> {
                loggerManager.initFragment(this)
                appState.isNetworkAvailable = networkManager.checkNetworkAvailable()
            }
            FragmentLifecycleEvent.ON_START -> {
                loggerManager.fragmentOnStart()
            }
            FragmentLifecycleEvent.ON_RESUME -> {
                loggerManager.fragmentOnResume()

            }
            FragmentLifecycleEvent.ON_PAUSE -> {
                loggerManager.fragmentOnPause()

            }
            FragmentLifecycleEvent.ON_STOP -> {
                loggerManager.fragmentOnStop()

            }
            FragmentLifecycleEvent.ON_DESTROY_VIEW -> {
                loggerManager.fragmentOnDestroyView()
                _binding = null
            }
            FragmentLifecycleEvent.ON_DESTROY -> {
                loggerManager.fragmentOnDestroy()
            }
        }
    }

}