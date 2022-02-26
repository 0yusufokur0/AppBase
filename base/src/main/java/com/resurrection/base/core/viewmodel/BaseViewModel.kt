package com.resurrection.base.core.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.component.*
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var loggerManager: LoggerManager
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var networkManager: NetworkManager
    @Inject lateinit var securityManager: SecurityManager
    @Inject lateinit var biometricManager: BiometricManager


    fun <T> MutableLiveData<Resource<T>>.setData(
        condition: Boolean = true,
        request: suspend () -> Flow<Resource<T>>
    ) = viewModelScope.launch {
        if (condition) {
            request()
                .onStart { this@setData.postValue(Resource.Loading()) }
                .catch { this@setData.postValue(Resource.Error(it)) }
                .collect { this@setData.postValue(it) }
        } else {
            this@setData.postValue(Resource.InValid(ThrowableError("request parameters is invalid")))
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
            this@setData.value = Resource.InValid(ThrowableError("request parameters is invalid"))
        }
    }

}