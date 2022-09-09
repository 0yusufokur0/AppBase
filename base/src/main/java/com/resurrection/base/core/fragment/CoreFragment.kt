package com.resurrection.base.core.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.datastore.DataStoreManager
import com.resurrection.base.components.typeconverter.TypeConverter
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.widget.AppLoadingIndicator
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.Status
import javax.inject.Inject

open class CoreFragment : Fragment() {

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