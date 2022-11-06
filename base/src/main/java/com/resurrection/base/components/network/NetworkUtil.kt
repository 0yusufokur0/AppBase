package com.resurrection.base.components.network

import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


@JvmName("resourcefulFlowOfRetrofit")
fun <T> resourcefulFlowOfRemote(request: suspend () -> Response<T>) = flow {
    val response = try {
        Resource.Success(request.invoke().body())
    } catch (e: Exception) {
        Resource.Error(e)
    }
    emit(response)
}
@JvmName("resourcefulFlowOfRoom")
fun <T> resourcefulFlowOfLocal(request: suspend () -> T) = flow {
    val response = try {
        Resource.Success(request.invoke())
    } catch (e: Exception) {
        Resource.Error(e)
    }
    emit(response)
}