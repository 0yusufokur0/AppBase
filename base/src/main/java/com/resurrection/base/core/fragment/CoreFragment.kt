package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.resurrection.base.component.*
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import com.resurrection.base.util.Status
import javax.inject.Inject

abstract class CoreFragment : Fragment() {

    @Inject
    lateinit var appState: AppState
    @Inject
    lateinit var dataHolder: DataHolderManager
    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager
    @Inject
    lateinit var loggerManager: LoggerManager
    @Inject
    lateinit var loadingIndicator: AppLoadingIndicator
    @Inject
    lateinit var networkManager: NetworkManager
    @Inject
    lateinit var securityManager: SecurityManager

    abstract fun init(savedInstanceState: Bundle?)

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
        this.observe(this@CoreFragment) { data ->
            when (data.status) {
                Status.SUCCESS -> success.invoke(data.data)
                Status.LOADING -> loading?.invoke()
                Status.ERROR -> error?.invoke()
                else -> ThrowableError("${data.data} fetch error")
            }
        }
    }
}