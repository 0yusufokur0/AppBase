package com.resurrection.base.components.logger

import android.os.Bundle
import com.resurrection.base.R
import com.resurrection.base.components.logger.analytics.AnalyticsLoggerManagerImpl
import com.resurrection.base.core.activity.CoreActivity

import com.resurrection.base.databinding.ActivityLoggerMonitorBinding
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import javax.inject.Inject

class LoggerMonitorActivity : CoreActivity(R.layout.activity_logger_monitor) {

    val binding by dataBinding<ActivityLoggerMonitorBinding>()
    val viewModel by viewModel<LoggerMonitorViewModel>()

    @Inject
    lateinit var analyticsLogger: AnalyticsLoggerManagerImpl

    override fun init(savedInstanceState: Bundle?) {
    }
}