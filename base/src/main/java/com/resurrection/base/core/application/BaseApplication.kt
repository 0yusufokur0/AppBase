package com.resurrection.base.core.application

import androidx.multidex.MultiDexApplication

abstract class BaseApplication : MultiDexApplication() {

    protected abstract val crashListenerEnabled: Boolean
    abstract fun init()

    abstract fun crashListener(thread: Thread, throwable: Throwable)

    override fun onCreate() {
        super.onCreate()
        initCrashTracker()
        init()
    }

    private fun initCrashTracker() {
        if (crashListenerEnabled) {
            Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
                crashListener(thread, throwable)
            }
        }
    }
}
