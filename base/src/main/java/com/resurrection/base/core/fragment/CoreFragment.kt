package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.datastore.DataStoreManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.typeconverter.TypeConverter
import com.resurrection.base.components.widget.AppLoadingIndicator
import com.resurrection.base.extensions.observeData
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.getPrivatePropertyValueByIndex
import javax.inject.Inject

abstract class CoreFragment @ContentView constructor(@LayoutRes val layoutRes: Int) : Fragment(layoutRes) {

    // region components
    @Inject
    lateinit var appState: AppState

    @Inject
    lateinit var dataHolder: DataHolderManager

    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var loggerManager: LoggerManager

    @Inject
    lateinit var loadingIndicator: AppLoadingIndicator

    @Inject
    lateinit var securityManager: SecurityManager

    @Inject
    lateinit var typeConverter: TypeConverter
    // endregion

    abstract fun init(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return callOnCreateViewSuper(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callOnViewCreatedSuper(view, savedInstanceState)
        init(view, savedInstanceState)
    }

    fun callOnViewCreatedSuper(
        view: View,
        savedInstanceState: Bundle?
    ) = super.onViewCreated(view, savedInstanceState)

    fun callOnCreateViewSuper(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = super.onCreateView(inflater, container, savedInstanceState)

    fun getLayoutId() = getPrivatePropertyValueByIndex(9)

    inline fun <T> LiveData<Resource<T>>.observeData(
        crossinline success: (T?) -> Unit = { },
        crossinline loading: () -> Unit = { },
        crossinline error: (Throwable?) -> Unit = { }
    ) = this.observeData(this@CoreFragment, success, loading, error)
}