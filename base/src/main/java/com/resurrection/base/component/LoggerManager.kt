package com.resurrection.base.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.resurrection.base.util.createFile
import com.resurrection.base.util.createFolder
import java.text.SimpleDateFormat
import java.util.*

class LoggerManager(private val context: Context) {

    private var saveState = false
    private val _logList = mutableListOf<String>()
    private val LOG_TAG = "AppLogger"
    private val appName = getApplicationName(context)
    private var activityName = ""
    private var fragmentName = ""
    private var rootPath = "" + Environment.getExternalStorageDirectory().absolutePath
    private val logPath = Environment.getExternalStorageDirectory().absolutePath + "/" + appName + "/Logs"
    val logList: List<String> = _logList

    fun init(saveState: Boolean) { this.saveState = saveState }

    fun initActivity(activityName: String) { this.activityName = activityName }

    fun initFragment(fragmentName:String) { this.fragmentName = fragmentName }

    // region log
    fun d(message: String) {
        if (saveState) _logList.add(message)
        Log.d(LOG_TAG, message)
    }

    fun e(message: String) {
        if (saveState) _logList.add(message)
        Log.e(LOG_TAG, message)
    }

    fun i(message: String) {
        if (saveState) _logList.add(message)
        Log.i(LOG_TAG, message)
    }

    fun v(message: String) {
        if (saveState) _logList.add(message)
        Log.v(LOG_TAG, message)
    }

    fun w(message: String) {
        if (saveState) _logList.add(message)
        Log.w(LOG_TAG, message)
    }

    fun wtf(message: String) {
        if (saveState) _logList.add(message)
        Log.wtf(LOG_TAG, message)
    }

    fun clearLogList() = _logList.clear()
    // endregion

    fun record() {
        saveState = true
        activityOnCreate()
        _logList.clear()
    }

    // region Activity lifecycle
    fun activityOnCreate() {
        if (saveState) {
            rootPath = createFolder(rootPath, "IMKB")
            rootPath = createFolder(rootPath, "Logs")
            rootPath = createFolder(rootPath, getDateTime())
            createFolder(rootPath, activityName)
        }
        d("Activity Create : $activityName")
    }

    fun activityOnStart() = this.d("Activity OnStart : $activityName")
    fun activityOnResume() = this.d("Activity OnResume : $activityName")
    fun activityOnPause() = this.d("Activity OnPause : $activityName")
    fun activityOnStop() {
        this.d("Activity OnStop : $activityName")
        if (saveState) createFile("$rootPath/$activityName", "Log", _logList)
        _logList.clear()
    }

    fun activityOnDestroy() = this.d("Activity OnDestroy : $activityName")
    fun activityOnRestart() = this.d("Activity OnRestart : $activityName")
    // endregion

    // region Fragment lifecycle
    fun fragmentOnCreate() = _logList.add("Fragment Create : $fragmentName")
    fun fragmentOnCreateView() = _logList.add("Fragment OnCreateView : $fragmentName")
    fun fragmentOnStart() = d("Fragment OnStart : $fragmentName")
    fun fragmentOnResume() = d("Fragment OnResume : $fragmentName")
    fun fragmentOnPause() = d("Fragment OnPause : $fragmentName")
    fun fragmentOnStop() = d("Fragment OnStop : $fragmentName")
    fun fragmentOnDestroyView() = d("Fragment OnDestroyView : $fragmentName")
    fun fragmentOnDestroy() = d("Fragment OnDestroy : $fragmentName")
    // endregion

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime() = SimpleDateFormat("dd_mm_yyyy-hh_mm_ss").format(Date())

    private fun getApplicationName(context: Context): String {
        val applicationInfo: ApplicationInfo = context.applicationInfo
        val stringId: Int = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }
}