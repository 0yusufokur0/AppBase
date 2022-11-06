package com.resurrection.base.components.logger

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Environment
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import com.resurrection.base.components.lifecycle.util.addObserver
import com.resurrection.base.core.fragment.LifecycleFragment
import com.resurrection.base.utils.createFile
import com.resurrection.base.utils.createFolder
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import java.text.SimpleDateFormat
import java.util.*

class LoggerManagerImpl(context: Context): LoggerManager{

    private var saveState = false
    private val _logList = mutableListOf<String>()
    private val tag = "AppLogger"
    private val appName = getApplicationName(context)
    private var activityName = ""
    private var fragmentName = ""
    private var rootPath = "" + Environment.getExternalStorageDirectory().absolutePath
    private val logPath =
        Environment.getExternalStorageDirectory().absolutePath + "/" + appName + "/Logs"
    val logList: List<String> = _logList



    override fun initApp(saveState: Boolean) {
        this.saveState = saveState
    }

    override fun initActivity(lifecycle: Lifecycle, activityName: String) {
        this.activityName = activityName
        lifecycle.addObserver { source, event ->
            when (event) {
                ON_CREATE -> activityOnCreate()
                ON_START -> activityOnStart()
                ON_RESUME -> activityOnResume()
                ON_PAUSE -> activityOnPause()
                ON_STOP -> activityOnStop()
                ON_DESTROY -> activityOnDestroy()
                else -> Unit
            }
        }
    }

    override fun initFragment(lifecycleFragment: LifecycleFragment, fragmentName: String) {
        this.fragmentName = fragmentName
        lifecycleFragment.addObserver { owner, event ->
            when (event){
                FragmentLifecycleEvent.ON_CREATE -> fragmentOnCreate()
                FragmentLifecycleEvent.ON_CREATE_VIEW -> fragmentOnCreateView()
                FragmentLifecycleEvent.ON_START -> fragmentOnStart()
                FragmentLifecycleEvent.ON_RESUME -> fragmentOnResume()
                FragmentLifecycleEvent.ON_PAUSE -> fragmentOnPause()
                FragmentLifecycleEvent.ON_STOP -> fragmentOnStop()
                FragmentLifecycleEvent.ON_DESTROY_VIEW -> fragmentOnDestroyView()
                FragmentLifecycleEvent.ON_DESTROY -> fragmentOnDestroy()
                else ->Unit
            }
        }
    }

    // region log
    fun d(message: String) {
        if (saveState) _logList.add(message)
        Log.d(tag, message)
    }

    fun e(message: String) {
        if (saveState) _logList.add(message)
        Log.e(tag, message)
    }

    fun i(message: String) {
        if (saveState) _logList.add(message)
        Log.i(tag, message)
    }

    fun v(message: String) {
        if (saveState) _logList.add(message)
        Log.v(tag, message)
    }

    fun w(message: String) {
        if (saveState) _logList.add(message)
        Log.w(tag, message)
    }

    fun wtf(message: String) {
        if (saveState) _logList.add(message)
        Log.wtf(tag, message)
    }

    fun clearLogList() = _logList.clear()
    // endregion

    fun record() {
        saveState = true
        activityOnCreate()
        _logList.clear()
    }

    // region Activity lifecycle
    private fun activityOnCreate() {
        if (saveState) {
            rootPath = createFolder(rootPath, "IMKB")
            rootPath = createFolder(rootPath, "Logs")
            rootPath = createFolder(rootPath, getDateTime())
            createFolder(rootPath, activityName)
        }
        d("Activity Create : $activityName")
    }

    private fun activityOnStart() = this.d("Activity OnStart : $activityName")
    private fun activityOnResume() = this.d("Activity OnResume : $activityName")
    private fun activityOnPause() = this.d("Activity OnPause : $activityName")
    private fun activityOnStop() {
        this.d("Activity OnStop : $activityName")
        if (saveState) createFile("$rootPath/$activityName", "Log", _logList)
        _logList.clear()
    }

    private fun activityOnDestroy() = this.d("Activity OnDestroy : $activityName")
    // endregion

    // region Fragment lifecycle
    private fun fragmentOnCreate() = _logList.add("Fragment Create : $fragmentName")
    private fun fragmentOnCreateView() = _logList.add("Fragment OnCreateView : $fragmentName")
    private fun fragmentOnStart() = d("Fragment OnStart : $fragmentName")
    private fun fragmentOnResume() = d("Fragment OnResume : $fragmentName")
    private fun fragmentOnPause() = d("Fragment OnPause : $fragmentName")
    private fun fragmentOnStop() = d("Fragment OnStop : $fragmentName")
    private fun fragmentOnDestroyView() = d("Fragment OnDestroyView : $fragmentName")
    private fun fragmentOnDestroy() = d("Fragment OnDestroy : $fragmentName")
    // endregion

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime() = SimpleDateFormat("dd_mm_yyyy-hh_mm_ss").format(Date())

    private fun getApplicationName(context: Context): String {
        val applicationInfo: ApplicationInfo = context.applicationInfo
        val stringId: Int = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
            stringId
        )
    }
}