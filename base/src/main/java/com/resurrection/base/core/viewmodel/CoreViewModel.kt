package com.resurrection.base.core.viewmodel

import androidx.lifecycle.*
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.callPrivateFunctionWithIndex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class CoreViewModel : ViewModel() {

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

    private fun handleFlowRequest() {
    }

    fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

    fun <T> liveData(): LiveData<T> = liveData { }

    protected fun <T> LiveData<T>.postValue(data: T) = this.callPrivateFunctionWithIndex(11, this, data)

    @PublishedApi
    internal fun <T> LiveData<Resource<T>>.accessPostValue(data: Resource<T>) = postValue(data)
}