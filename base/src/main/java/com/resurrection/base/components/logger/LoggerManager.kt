package com.resurrection.base.components.logger

import androidx.lifecycle.Lifecycle
import com.resurrection.base.core.fragment.CoreFragment

interface LoggerManager {
    fun initApp(saveState: Boolean)
    fun initActivity(lifecycle: Lifecycle, activityName: String)
    fun initFragment(lifecycleFragment: CoreFragment, fragmentName: String)

    fun d(message: String)
    fun e(message: String)
    fun i(message: String)
    fun v(message: String)
    fun w(message: String)
    fun wtf(message: String)

    fun clearLogList()
    fun record()
}