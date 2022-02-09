package com.resurrection.base.core

import androidx.lifecycle.*
import com.resurrection.base.component.AppState
import com.resurrection.base.component.DataHolderManager
import com.resurrection.base.component.SharedPreferencesManager
import com.resurrection.base.component.Logger
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import com.resurrection.base.component.AppLoadingIndicator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator

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