package com.resurrection.base.core.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.component.*
import javax.inject.Inject

abstract class CoreActivity : AppCompatActivity(), LifecycleEventObserver {
    @Inject
    lateinit var appState: AppState
    @Inject
    lateinit var dataHolder: DataHolderManager
    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager
    @Inject
    lateinit var loggerManager: LoggerManager
    @Inject
    lateinit var loadingIndicator: AppLoadingIndicator
    @Inject
    lateinit var networkManager: NetworkManager
    @Inject
    lateinit var securityManager: SecurityManager
    @Inject
    lateinit var biometricManager: BiometricManager

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                appState.isRooted = securityManager.isRooted()
                loadingIndicator.init(this)
                loggerManager.activityOnCreate()
                loggerManager.initActivity(this)
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