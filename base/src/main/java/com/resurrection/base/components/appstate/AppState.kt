package com.resurrection.base.components.appstate

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.utils.BaseConstants
import javax.inject.Inject

class AppState @Inject constructor(
    private val context: Context,
    private val dataHolderManager: DataHolderManager,
    private val networkManager: NetworkManager,
    private val securityManager: SecurityManager
) {
    val isAppForeground: Boolean
        get() = dataHolderManager.getBoolean(BaseConstants.IS_FOREGROUND, false)

    val isAppLightMode: Boolean
        get() = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES

    val isNetworkAvailable: Boolean
        get() = networkManager.checkNetworkAvailable()

    val isRooted: Boolean
        get() = securityManager.isRooted()
}
