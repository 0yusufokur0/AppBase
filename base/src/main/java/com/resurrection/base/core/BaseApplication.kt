package com.resurrection.base.core

import androidx.multidex.MultiDexApplication
import com.resurrection.base.component.AppState
import com.resurrection.base.component.DataHolderManager
import com.resurrection.base.component.SharedPreferencesManager
import com.resurrection.base.component.Logger
import com.resurrection.base.component.AppLoadingIndicator
import javax.inject.Inject

open class BaseApplication  : MultiDexApplication(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator


}