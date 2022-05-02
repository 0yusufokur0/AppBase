package com.resurrection.base.component

import android.content.Context
import com.resurrection.base.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class OkHttpClientManager @Inject constructor(
    val typeConverter: TypeConverter
) {
    companion object {
        const val GET = "GET"
        const val POST = "POST"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
    }

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var baseUrl: String


    fun init(okHttpClient: OkHttpClient,baseUrl: String) {
        this.baseUrl = baseUrl
        this.okHttpClient = okHttpClient
    }

    fun <T> newRequest(
        okHttpClient: OkHttpClient = this.okHttpClient,
        baseUrl: String = this.baseUrl,
        method: String = GET,
        body: String? = null,
        headers: Map<String, String>? = null,
        path: String,
        responseType: Class<T>,
        map:(responseBody: String) -> T =  { typeConverter.fromJson(it, responseType) }
    ) = flow {
        val requestBuilder = Request.Builder()
            .url(baseUrl + path)
            .method(method, body?.toRequestBody())

        headers?.let { requestBuilder.headers(headers.toHeaders()) }

        val request = requestBuilder.build()

        var resultResource: Resource<T>? = null
        try {
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.apply {
                    val mapResult = map(this.string())
                    resultResource = if (map != null) Resource.Success(mapResult)
                    else Resource.Error(Exception("map is null"))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            resultResource = Resource.Error(e)
        }
        if (resultResource == null) resultResource = Resource.Loading()

        emit(resultResource!!)
    }.flowOn(Dispatchers.IO)
}