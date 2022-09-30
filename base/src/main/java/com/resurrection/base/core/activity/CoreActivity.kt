package com.resurrection.base.core.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.datastore.DataStoreManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.security.BiometricManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.typeconverter.TypeConverter
import com.resurrection.base.components.widget.AppLoadingIndicator
import com.resurrection.base.extensions.observeData
import com.resurrection.base.utils.Resource
import javax.inject.Inject

abstract class CoreActivity @ContentView constructor(@LayoutRes val layoutRes: Int) : AppCompatActivity(layoutRes) {

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
    lateinit var biometricManager: BiometricManager

    @Inject
    lateinit var typeConverter: TypeConverter
    // endregion

    // region constructors

    // endregion

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        callOnCreateSuper(savedInstanceState)
        init(savedInstanceState)
    }

    fun callOnCreateSuper(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    inline fun <T> LiveData<Resource<T>>.observeData(
        crossinline success: (T?) -> Unit = { },
        crossinline loading: () -> Unit = { },
        crossinline error: (Throwable?) -> Unit = { }
    ) = observeData(this@CoreActivity, success, loading, error)
}
