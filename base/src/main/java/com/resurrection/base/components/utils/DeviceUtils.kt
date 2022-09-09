package com.resurrection.base.components.utils

class DeviceUtils {

    // get device name
    fun getDeviceName(): String {
        return android.os.Build.MODEL
    }

    // get device version
    fun getDeviceVersion(): String {
        return android.os.Build.VERSION.RELEASE
    }

    // get device manufacturer
    fun getDeviceManufacturer(): String {
        return android.os.Build.MANUFACTURER
    }

    // get android version
    fun getAndroidVersion(): String {
        return android.os.Build.VERSION.SDK_INT.toString()
    }
}