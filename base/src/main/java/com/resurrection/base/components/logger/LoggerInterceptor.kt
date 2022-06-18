package com.resurrection.base.components.logger

import com.resurrection.base.utils.DateTimeUtils
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
        val responseCode = response.code
        val responseMessage = response.message
        val responseBody =
            response.body?.source()?.buffer?.clone()?.readString(StandardCharsets.UTF_8)

        val requestLogString =
            "\n" +
                    "\t URL: $requestUrl \n" +
                    "\t Method: $requestMethod \n" +
                    "\t Headers: $requestHeaders \n" +
                    "\t Body: $requestBody \n"

        val responseLogString =
            "\n" +
                    "\t Headers: $responseHeaders \n" +
                    "\t Message: $responseMessage \n" +
                    "\t Code: $responseCode \n" +
                    "\t Body: $responseBody \n"

        val resultLogString =
            "New Request Started... \n" +
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