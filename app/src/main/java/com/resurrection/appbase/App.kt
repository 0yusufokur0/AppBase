package com.resurrection.appbase

import android.content.Intent
import android.os.Process
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.multidex.MultiDexApplication
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.crashtracker.CrashTrackerActivity
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltAndroidApp
class App : MultiDexApplication(), LifecycleEventObserver {

    @Inject
    lateinit var appState: AppState

    override fun onCreate() {
        super.onCreate()
        crashListener()
    }

    fun crashListener() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            startActivity(Intent(this, CrashTrackerActivity::class.java))
            Process.killProcess(Process.myPid())
            exitProcess(0)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            ON_START -> appState.isAppForeground = true
            ON_STOP -> appState.isAppForeground = false
            else -> Unit
        }
    }
}