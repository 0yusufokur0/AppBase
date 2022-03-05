package com.resurrection.appbase

import com.example.processor.MyConstant
import com.resurrection.base.core.application.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApplication()
@MyConstant("myValue","myProp")
fun main() {

}