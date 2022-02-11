package com.resurrection.appbase

import com.resurrection.base.core.BaseApplication
import com.resurrection.base.model.Variant

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApplication() {
    override val buildConfigClass = BuildConfig::class.java
    override val variantList = mutableListOf(
        Variant(variantName = "production"),
        Variant(variantName = "development")
    )
}
