package com.resurrection.base.components.logger

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.resurrection.base.core.fragment.LifecycleFragment

interface LoggerManager {
    fun initApp(saveState: Boolean)
    fun initActivity(lifecycle: Lifecycle,activityName: String)
    fun initFragment(lifecycleFragment: LifecycleFragment,fragmentName: String)

}