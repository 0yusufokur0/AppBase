package com.resurrection.base.core

import com.resurrection.base.component.AppState
import com.resurrection.base.component.DataHolderManager
import com.resurrection.base.component.SharedPreferencesManager
import com.resurrection.base.component.Logger
import com.resurrection.base.util.Resource
import com.resurrection.base.component.AppLoadingIndicator
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

abstract class BaseRepository {

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator


    @JvmName("getData1")
    fun <T> getData(request: suspend () -> Response<T>) =  flow { emit(getResourceByNetworkRequest { request() }) }
    fun <T> getData(request: suspend () -> T) = flow { emit(getResourceByDatabaseRequest { request() }) }

    suspend fun <T> getResourceByNetworkRequest(request: suspend () -> Response<T>): Resource<T> {
        try {
            val response = request()
            if (response.isSuccessful) {
                response.body()?.apply {
                    return Resource.Success(this)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(e)
        }
        return Resource.Loading()
    }

    suspend fun <T> getResourceByDatabaseRequest(request: suspend () -> T): Resource<T> {
        try {
            val result = request()
            result?.let {
                return Resource.Success(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(e)
        }
        return Resource.Loading()
    }
}