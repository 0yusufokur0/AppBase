package com.resurrection.base.core.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class CoreViewModel : ViewModel() {

    @Inject
    lateinit var appState: AppState

    @Inject
    lateinit var dataHolder: DataHolderManager

    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager

    @Inject
    lateinit var loggerManager: LoggerManager

    @Inject
    lateinit var networkManager: NetworkManager

    inline fun <T> MutableLiveData<Resource<T>>.fetchData(
        crossinline condition: () -> Boolean = { true },
        crossinline request: suspend () -> Flow<Resource<T>>,
        crossinline success: (Resource<T>) -> Unit = { this.postValue(it) },
        crossinline loading: () -> Unit = { this.postValue(Resource.Loading()) },
        crossinline error: (Throwable) -> Unit = { this.postValue(Resource.Error(it)) }
    ) = viewModelScope.launch {
        if (condition()) {
            request()
                .onStart { loading() }
                .catch { error(it) }
                .collect { success(it) }
        }
    }

    @JvmName("fetchDataT")
    fun <T> MutableLiveData<T>.fetchData(
        condition: () -> Boolean = { true },
        request: suspend () -> Flow<T>,
        success: (T) -> Unit = { },
        loading: () -> Unit = { },
        error: (Throwable) -> Unit = { }
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

    fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this
}