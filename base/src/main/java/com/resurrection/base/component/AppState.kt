package com.resurrection.base.component

import javax.inject.Inject

data class AppState @Inject constructor(
    var isAppForeground: Boolean,
    var isAppLightMode: Boolean,
    var isNetworkAvailable: Boolean,
    var isRooted: Boolean
)