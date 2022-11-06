package com.resurrection.base.components.logger

import androidx.lifecycle.Lifecycle
import com.resurrection.base.core.fragment.LifecycleFragment

interface LoggerManager {
    fun initApp(saveState: Boolean)
    fun initActivity(lifecycle: Lifecycle,activityName: String)
    fun initFragment(lifecycleFragment: LifecycleFragment,fragmentName: String)

    fun d(message: String)
    fun e(message: String)
    fun i(message: String)
    fun v(message: String)
    fun w(message: String)
    fun wtf(message: String)

    fun clearLogList()
    fun record()
}