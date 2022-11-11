package com.resurrection.base.components.crashtracker

import android.os.Bundle
import com.resurrection.base.R
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.databinding.ActivityCrashTrackerBinding
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrashTrackerActivity : CoreActivity(R.layout.activity_crash_tracker) {

    val binding by dataBinding<ActivityCrashTrackerBinding>()
    val viewModel by viewModel<CrashTrackerViewModel>()

    override fun init(savedInstanceState: Bundle?) {
    }
}
