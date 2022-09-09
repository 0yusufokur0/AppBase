package com.resurrection.base.components.logger.analytics

import com.resurrection.base.components.logger.LoggerManager
import javax.inject.Inject

class AnalyticsLoggerManagerImpl constructor(
      private val loggerManager: LoggerManager
) : AnalyticsLoggerManager {

    override fun debug(tag: String, message: String) {
        TODO("Not yet implemented")
    }

    override fun error(tag: String, message: String) {
        TODO("Not yet implemented")
    }

    override fun performance(tag: String, message: String) {
        TODO("Not yet implemented")
    }

    override fun push(analyticsLogType: AnalyticsLogType, tag: String, message: String) {
        TODO("Not yet implemented")
    }

}