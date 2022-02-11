package com.resurrection.base.core

import androidx.multidex.MultiDexApplication
import com.resurrection.base.component.*
import com.resurrection.base.model.Variant
import java.lang.Exception
import javax.inject.Inject

abstract class BaseApplication  : MultiDexApplication(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var configuration: Configuration
    protected abstract val buildConfigClass: Class<*>
    protected abstract val variantList:MutableList<Variant>
    var currentVariantConfig : Variant? = null


    override fun onCreate() {
        super.onCreate()

        val currentVariantName = buildConfigClass.getField("FLAVOR").get(null)

        variantList.forEach { variant ->
            if (currentVariantName == variant.variantName) currentVariantConfig = variant
        }

        configuration.config = currentVariantConfig

    }


}

