package com.resurrection.base.core.application

import androidx.multidex.MultiDexApplication
import com.resurrection.base.component.*
import javax.inject.Inject

abstract class BaseApplication() : MultiDexApplication(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var loggerManager: LoggerManager
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var networkManager: NetworkManager
    @Inject lateinit var securityManager: SecurityManager
    @Inject lateinit var biometricManager: BiometricManager

    override fun onCreate() {
        super.onCreate()
        loggerManager.init(false)
    }
}

