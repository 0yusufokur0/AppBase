package com.resurrection.base.components.network

import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.Response


@JvmName("getData1")
fun <T> getData(request: suspend () -> Response<T>) =
    flow { emit(getResourceByNetworkRequest { request() }) }

fun <T> getData(request: suspend () -> T) =
    flow { emit(getResourceByDatabaseRequest { request() }) }

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