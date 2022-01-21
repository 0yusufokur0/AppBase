package com.resurrection.base.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.base.general.ThrowableError
import com.resurrection.base.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(){

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


}