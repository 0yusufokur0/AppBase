package com.resurrection.base.core

import androidx.lifecycle.ViewModelProvider
import androidx.multidex.MultiDexApplication
import com.resurrection.base.data.AppState
import com.resurrection.base.data.DataHolderManager
import com.resurrection.base.data.SharedPreferencesManager
import com.resurrection.base.general.Logger
import com.resurrection.base.widget.AppLoadingIndicator
import javax.inject.Inject

open class BaseApplication  : MultiDexApplication(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator


}