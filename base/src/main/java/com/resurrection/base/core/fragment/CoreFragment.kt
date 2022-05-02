package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.resurrection.base.component.*
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
    @Inject
    lateinit var typeConverter: TypeConverter
    @Inject
    lateinit var dataStoreManager: DataStoreManager

    abstract fun init(savedInstanceState: Bundle?)

    fun <T> LiveData<Resource<T>>.observeData(
        success: (T?) -> Unit,
        loading: (() -> Unit)? = null,
        error: ((Throwable?) -> Unit)? = null
    ) {
        this.observe(this@CoreFragment) { resource ->
            when (resource.status) {
                Status.SUCCESS -> success.invoke(resource.data)
                Status.LOADING -> loading?.invoke()
                Status.ERROR -> error?.invoke(resource.error)
                else -> Throwable("${resource.data} fetch error")
            }
        }
    }
}