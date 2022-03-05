package com.example.processor

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(SOURCE)
@Target(FUNCTION)
annotation class MyConstant(
    val propName: String,
    val propValue: String
)