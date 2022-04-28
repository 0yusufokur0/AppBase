package com.resurrection.base.core.network

import kotlinx.coroutines.DisposableHandle
import okhttp3.Interceptor
import okhttp3.Response

abstract class LoggerInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .build()

        val response = chain.proceed(request)
        return response
    }
}