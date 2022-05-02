package com.resurrection.appbase

import com.resurrection.base.core.application.BaseApplication
import com.resurrection.base.components.logger.LoggerInterceptor
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class App : BaseApplication() {
    override fun init() {
        initOkHttpClient()
    }

    private fun initOkHttpClient(){
        val okHttpClient =
            OkHttpClient()
                .newBuilder()
                .addInterceptor(LoggerInterceptor(loggerManager))
                .build()
        okHttpClientManager.init(okHttpClient,BuildConfig.API_URL)
    }
}