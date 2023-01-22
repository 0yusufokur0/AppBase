package com.resurrection.base.components.appstate

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import com.resurrection.base.components.dataholder.DataHolder
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.utils.BaseConstants.IS_FOREGROUND

class AppState(
    private val context: Context,
    private val dataHolder: DataHolder,
    private val networkManager: NetworkManager,
    private val securityManager: SecurityManager
) {
    var isAppForeground: Boolean
        get() = dataHolder.getBoolean(IS_FOREGROUND, false)
        set(value) = dataHolder.putBoolean(IS_FOREGROUND, value)

    val isAppLightMode: Boolean
        get() = (context.resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES

    val isNetworkAvailable: Boolean
        get() = networkManager.checkNetworkAvailable()

    val isRooted: Boolean
        get() = securityManager.isRooted()
}
