package com.resurrection.base.data

import android.text.style.ForegroundColorSpan
import javax.inject.Inject

data class AppState @Inject constructor(
    var isAppForeground:Boolean,
    var isAppLightMode:Boolean
)