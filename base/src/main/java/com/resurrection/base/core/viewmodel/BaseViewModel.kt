package com.resurrection.base.core.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.security.BiometricManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.typeconverter.TypeConverter
import com.resurrection.base.components.widget.AppLoadingIndicator
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.callPrivateFunc
import com.resurrection.base.utils.callPrivateFunctionWithIndex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

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
    lateinit var biometricManager: BiometricManager

    @Inject
    lateinit var typeConverter: TypeConverter


    inline fun <T> LiveData<Resource<T>>.fetchData(
        crossinline condition: () -> Boolean = { true },
        crossinline request: suspend () -> Flow<Resource<T>>,
        crossinline success: (Resource<T>) -> Unit = { this.accessPostValue(it) },
        crossinline loading: () -> Unit = { this.accessPostValue(Resource.Loading()) },
        crossinline error: (Throwable) -> Unit = { this.accessPostValue(Resource.Error(it)) }
    ) = viewModelScope.launch {
        if (condition()) {
            request()
                .onStart { loading() }
                .catch { error(it) }
                .collect { success(it) }
        }
    }

    fun <T> MutableStateFlow<Resource<T>>.setData(
        condition: Boolean = true,
        request: suspend () -> Flow<Resource<T>>
    ) = viewModelScope.launch {
        if (condition) {
            request()
                .onStart { this@setData.value = Resource.Loading() }
                .catch { this@setData.value = Resource.Error(it) }
                .collect { this@setData.value = it }
        }
    }

    fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> = this

    fun <T> liveData(): LiveData<T> = liveData { }

    protected fun <T> LiveData<T>.postValue(data: T) = this.callPrivateFunctionWithIndex(11, this,data)

    @PublishedApi
    internal fun <T> LiveData<Resource<T>>.accessPostValue(data: Resource<T>) = postValue(data)

}