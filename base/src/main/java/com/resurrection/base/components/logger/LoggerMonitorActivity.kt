package com.resurrection.base.components.logger

import android.os.Bundle
import com.resurrection.base.R
import com.resurrection.base.components.logger.analytics.AnalyticsLoggerManagerImpl

import com.resurrection.base.core.activity.BaseActivity
import com.resurrection.base.databinding.ActivityLoggerMonitorBinding
import javax.inject.Inject

class LoggerMonitorActivity : BaseActivity<ActivityLoggerMonitorBinding, LoggerMonitorViewModel>(
    R.layout.activity_logger_monitor, LoggerMonitorViewModel::class.java
) {


    @Inject
    lateinit var analyticsLogger: AnalyticsLoggerManagerImpl

    override fun init(savedInstanceState: Bundle?) {

    }


}