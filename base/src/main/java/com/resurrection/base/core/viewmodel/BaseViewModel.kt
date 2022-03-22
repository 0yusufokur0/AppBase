package com.resurrection.base.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.base.component.*
import com.resurrection.base.util.Resource
import kotlinx.coroutines.flow.*
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


    fun <T> MutableLiveData<Resource<T>>.setData(
        condition: Boolean = true,
        request: suspend () -> Flow<Resource<T>>,
        success: (Resource<T>) -> Unit = { this@setData.postValue(it) },
        loading: () -> Unit = { this@setData.postValue(Resource.Loading()) },
        error: (Throwable) -> Unit = { this@setData.postValue(Resource.Error(it)) }
    ) = viewModelScope.launch {
        if (condition) {
            request()
                .onStart { loading.invoke() }
                .catch { error.invoke(it) }
                .collect { success.invoke(it) }
        } else {
            this@setData.postValue(Resource.InValid(Throwable("request parameters is invalid")))
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
        } else {
            this@setData.value = Resource.InValid(Throwable("request parameters is invalid"))
        }
    }

    fun <T> MutableLiveData<T>.toLiveData():LiveData<T> = this

}