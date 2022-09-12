package com.resurrection.base.components.crashtracker

import android.os.Bundle
import com.resurrection.base.R
import com.resurrection.base.core.activity.BaseActivity
import com.resurrection.base.databinding.ActivityCrashTrackerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrashTrackerActivity : BaseActivity<ActivityCrashTrackerBinding, CrashTrackerViewModel>(
    R.layout.activity_crash_tracker, CrashTrackerViewModel::class.java
) {
    override fun init(savedInstanceState: Bundle?) {

    }
}
