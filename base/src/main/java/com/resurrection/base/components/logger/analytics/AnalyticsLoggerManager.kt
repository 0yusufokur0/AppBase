package com.resurrection.base.components.logger.analytics

interface AnalyticsLoggerManager {

    fun debug(tag: String, message: String)

    fun error(tag: String, message: String)

    fun performance(tag: String, message: String)

    fun push(analyticsLogType: AnalyticsLogType, tag: String, message: String)


}