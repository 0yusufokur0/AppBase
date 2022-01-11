package com.resurrection.base


import com.resurrection.base.data.DataHolderManager
import com.resurrection.base.data.SharedPreferencesManager
import com.resurrection.base.general.Logger
import javax.inject.Inject

data class AppSession @Inject constructor(
    val dataHolder: DataHolderManager,
    val sharedPreferences: SharedPreferencesManager,
    val logger: Logger
)