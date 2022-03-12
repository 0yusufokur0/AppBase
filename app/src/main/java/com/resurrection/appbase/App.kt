package com.resurrection.appbase

import com.example.Main
import com.example.processor.AppConstant
import com.resurrection.base.core.application.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApplication()
@AppConstant("myValue","myProp")
fun main() {
    println(Main.myValue)
}