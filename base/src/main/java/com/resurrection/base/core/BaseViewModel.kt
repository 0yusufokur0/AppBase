package com.resurrection.base.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(){
    fun <T> MutableLiveData<Resource<T>>.getData(
        condition: Boolean = true,
        request: suspend () -> Flow<Resource<T>>
    ) = viewModelScope.launch {
        if (condition) {
            request()
                .onStart { this@getData.postValue(Resource.Loading()) }
                .catch { this@getData.postValue(Resource.Error(it)) }
                .collect { this@getData.postValue(it) }
        } else {
            this@getData.postValue(Resource.InValid(ThrowableError("request parameters is empty")))
        }
    }
}