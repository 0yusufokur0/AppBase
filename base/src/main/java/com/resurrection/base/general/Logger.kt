package com.resurrection.base.general

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import com.resurrection.base.util.createFile
import com.resurrection.base.util.createFolder
import java.text.SimpleDateFormat
import java.util.*


class Logger {
    private var saveState = false
    private val logList = mutableListOf<String>()
    private val LOG_TAG = "AppLogger"
    private var activityName = ""

    private var rootPath = "" + Environment.getExternalStorageDirectory().absolutePath
    private var logPath = "" + Environment.getExternalStorageDirectory().absolutePath + "/IMKB/Logs"

    // region log
    fun d(message: String) {
        logList.add(message)
        Log.d(LOG_TAG, message)
    }

    fun e(message: String) {
        logList.add(message)
        Log.e(LOG_TAG, message)
    }

    fun i(message: String) {
        logList.add(message)
        Log.i(LOG_TAG, message)
    }

    fun v(message: String) {
        logList.add(message)
        Log.v(LOG_TAG, message)
    }

    fun w(message: String) {
        logList.add(message)
        Log.w(LOG_TAG, message)
    }

    fun wtf(message: String) {
        logList.add(message)
        Log.wtf(LOG_TAG, message)
    }

    fun getLogList() = logList
    fun clearLogList() = logList.clear()
    // endregion

    fun record(){
        saveState = true
        activityOnCreate(activityName)
        logList.clear()
    }

    // region Activity lifecycle
    fun activityOnCreate(activityName: String) {
        this.activityName = activityName
        if (saveState){
            rootPath = createFolder(rootPath, "IMKB")
            rootPath = createFolder(rootPath, "Logs")
            rootPath = createFolder(rootPath, getDateTime())
            createFolder(rootPath, activityName)
        }
        this.d("Activity Create : $activityName")
        /* TODO: get logPath size
              val file = File(logPath)
              val size = file.length()
              if (size > 1000000) {
                  logPath = createFolder(rootPath, "Logs")
                  logPath = createFolder(logPath, getDateTime())
              }*/
    }

    fun activityOnStart(activityName: String) = this.d("Activity OnStart : $activityName")
    fun activityOnResume(activityName: String) = this.d("Activity OnResume : $activityName")
    fun activityOnPause(activityName: String) = this.d("Activity OnPause : $activityName")
    fun activityOnStop(activityName: String) {
        this.d("Activity OnStop : $activityName")
        if (saveState) createFile("$rootPath/$activityName", "Log", logList)
        logList.clear()
    }

    fun activityOnDestroy(activityName: String) = this.d("Activity OnDestroy : $activityName")
    fun activityOnRestart(activityName: String) = this.d("Activity OnRestart : $activityName")
    // endregion

    // region Fragment lifecycle
    fun fragmentOnCreate(fragmentName: String) = logList.add("Fragment Create : $fragmentName")
    fun fragmentOnCreateView(fragmentName: String) = logList.add("Fragment OnCreateView : $fragmentName")
    fun fragmentOnStart(fragmentName: String) = this.d("Fragment OnStart : $fragmentName")
    fun fragmentOnResume(fragmentName: String) = this.d("Fragment OnResume : $fragmentName")
    fun fragmentOnPause(fragmentName: String) = this.d("Fragment OnPause : $fragmentName")
    fun fragmentOnStop(fragmentName: String) = this.d("Fragment OnStop : $fragmentName")
    fun fragmentOnDestroyView(fragmentName: String) = this.d("Fragment OnDestroyView : $fragmentName")
    fun fragmentOnDestroy(fragmentName: String) = this.d("Fragment OnDestroy : $fragmentName")
    // endregion

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime() = SimpleDateFormat("dd_mm_yyyy-hh_mm_ss").format(Date())
}