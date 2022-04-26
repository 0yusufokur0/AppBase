package com.resurrection.base.general

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = BaseConstants.DATA_STORE_NAME)