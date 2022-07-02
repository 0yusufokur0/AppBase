package com.resurrection.base.core.activity

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.resurrection.base.utils.BaseConstants

open class LifecycleActivity : CoreActivity(),LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                dataHolder.putBoolean(BaseConstants.IS_FOREGROUND, true)
                loadingIndicator.init(this)
                loggerManager.activityOnCreate()
                loggerManager.initActivity(localClassName)
                biometricManager.init(this)
            }
            Lifecycle.Event.ON_START -> {
                dataHolder.putBoolean(BaseConstants.IS_FOREGROUND, true)
                loggerManager.activityOnStart()
            }
            Lifecycle.Event.ON_RESUME -> {
                loggerManager.activityOnResume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                loggerManager.activityOnPause()
            }
            Lifecycle.Event.ON_STOP -> {
                dataHolder.putBoolean(BaseConstants.IS_FOREGROUND, false)
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}