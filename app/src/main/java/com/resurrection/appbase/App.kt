package com.resurrection.appbase

import android.content.Intent
import android.os.Process
import com.resurrection.base.components.crashtracker.CrashTrackerActivity
import com.resurrection.base.components.logger.LoggerInterceptor
import com.resurrection.base.core.application.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import kotlin.system.exitProcess

@HiltAndroidApp
class App : BaseApplication() {

    override fun init() {
        initOkHttpClient()
    }

    override fun crashListener(thread: Thread, throwable: Throwable) {
        startActivity(Intent(this, CrashTrackerActivity::class.java))
        Process.killProcess(Process.myPid())
        exitProcess(0)
    }

    private fun initOkHttpClient() {
        val okHttpClient =
            OkHttpClient()
                .newBuilder()
                .addInterceptor(LoggerInterceptor(loggerManager))
                .build()
        okHttpClientManager.init(okHttpClient, BuildConfig.API_URL)
    }
}