package com.resurrection.base.core.network

import com.resurrection.base.component.LoggerManager
import com.resurrection.base.util.DateTimeUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

open class LoggerInterceptor(private val loggerManager: LoggerManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .build()
        val response = chain.proceed(request)
        logRequestAndResponse(request, response)
        return response
    }

    private fun logRequestAndResponse(request: Request, response: Response) {
    val requestTime = "Request Date Time: ${DateTimeUtils.getCurrentDateTime()}"
        val requestLogString =
                    "Request url: ${request.url } \n" +
                    "Request method: ${ request.method } \n" +
                    "Request headers: ${ request.headers } \n" +
                    "Request body: ${request.body } \n"


        val responseLogString =
                    "Request headers: ${ response.headers} \n" +
                    "Request body: ${response.body?.string() } \n"
                    "Request code: ${ response.code} \n"
                    "Request message  ${ response.message } \n"

        loggerManager.d("-------------------------------------------------------")
        loggerManager.d(requestTime)
        loggerManager.d(requestLogString)
        loggerManager.d(responseLogString)
        loggerManager.d("-------------------------------------------------------")
    }

}