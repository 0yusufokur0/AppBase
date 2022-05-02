package com.resurrection.base.extensions

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.resurrection.base.utils.BaseConstants

val Context.dataStore by preferencesDataStore(name = BaseConstants.DATA_STORE_NAME)