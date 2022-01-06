package com.resurrection.imkb.base

import com.resurrection.basesample.base.data.DataHolderManager
import com.resurrection.basesample.base.data.SharedPreferencesManager
import com.resurrection.basesample.base.general.Logger
import javax.inject.Inject

data class AppSession @Inject constructor(
    val dataHolder: DataHolderManager,
    val sharedPreferences: SharedPreferencesManager,
    val logger: Logger
)