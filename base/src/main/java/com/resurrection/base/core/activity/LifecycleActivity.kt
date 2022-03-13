package com.resurrection.base.core.activity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

abstract class LifecycleActivity: CoreActivity() {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                appState.isRooted = securityManager.isRooted()
                loadingIndicator.init(this)
                loggerManager.activityOnCreate()
                loggerManager.initActivity(localClassName)
                biometricManager.init(this)
            }
            Lifecycle.Event.ON_START -> {
                appState.isAppForeground = true
                loggerManager.activityOnStart()
            }
            Lifecycle.Event.ON_RESUME -> {
                loggerManager.activityOnResume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                loggerManager.activityOnPause()
            }
            Lifecycle.Event.ON_STOP -> {
                appState.isAppForeground = false
                loggerManager.activityOnStop()
            }
            Lifecycle.Event.ON_DESTROY -> {
                loggerManager.activityOnDestroy()
            }
            Lifecycle.Event.ON_ANY -> {
                loggerManager.activityOnRestart()
            }
        }
    }
}