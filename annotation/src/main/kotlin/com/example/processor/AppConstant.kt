package com.example.processor

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(SOURCE)
@Target(FUNCTION)
annotation class AppConstant(
    val propName: String,
    val propValue: String
)