package com.resurrection.base.core.network

import com.resurrection.base.component.LoggerManager
import com.resurrection.base.util.DateTimeUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.charset.StandardCharsets


open class LoggerInterceptor(private val loggerManager: LoggerManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().build()
        val tempResponse = chain.proceed(request)
        val tempBody = tempResponse.body

        val resultResponse = tempResponse
            .newBuilder()
            .body(tempBody?.string()?.toResponseBody(tempBody.contentType()))
            .build()

        logRequestAndResponse(request, resultResponse)

        return resultResponse

    }

    private fun logRequestAndResponse(request: Request, response: Response) {

        val requestTime = "\t Request Date Time: ${DateTimeUtils.getCurrentDateTime()}"
        val requestUrl = request.url.toString()
        val requestMethod = request.method
        val requestHeaders = request.headers.toString()
        val requestBody = request.body?.toString() ?: ""

        val responseHeaders = response.headers.toString()
        val responseBody = response.peekBody(Long.MAX_VALUE).string()
        val responseCode = response.code
        val responseMessage = response.message
        val mresponseBody = response.body?.source()?.buffer?.clone()?.readString(StandardCharsets.UTF_8)

        val requestLogString =
                    "\n" +
                    "\t URL: $requestUrl \n" +
                    "\t Method: $requestMethod \n" +
                    "\t Headers: $requestHeaders \n" +
                    "\t Body: $requestBody \n"
        val responseLogString =
                    "\n" +
                    "\t Headers: $responseHeaders \n" +
                    "\t Body: $mresponseBody \n" +
                    "\t Message: $responseMessage \n"+
                    "\t Code: $responseCode \n"
        val resultLogString =
                " .\n"+
                "┌────── Request ────────────────────────────────────────────────────────────────────────" +
                "\n" +
                requestTime +
                requestLogString +
                "└───────────────────────────────────────────────────────────────────────────────────────" +
                "\n" +
                "┌────── Response ───────────────────────────────────────────────────────────────────────" +
                responseLogString +
                "└───────────────────────────────────────────────────────────────────────────────────────"

        loggerManager.d(resultLogString)

    }

}