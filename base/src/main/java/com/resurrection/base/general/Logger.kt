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
    var activityName = ""
    var fragmentName = ""

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
        activityOnCreate()
        logList.clear()
    }

    // region Activity lifecycle
    fun activityOnCreate() {
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

    fun activityOnStart() = this.d("Activity OnStart : $activityName")
    fun activityOnResume() = this.d("Activity OnResume : $activityName")
    fun activityOnPause() = this.d("Activity OnPause : $activityName")
    fun activityOnStop() {
        this.d("Activity OnStop : $activityName")
        if (saveState) createFile("$rootPath/$activityName", "Log", logList)
        logList.clear()
    }

    fun activityOnDestroy() = this.d("Activity OnDestroy : $activityName")
    fun activityOnRestart() = this.d("Activity OnRestart : $activityName")
    // endregion

    // region Fragment lifecycle
    fun fragmentOnCreate() = logList.add("Fragment Create : $fragmentName")
    fun fragmentOnCreateView() = logList.add("Fragment OnCreateView : $fragmentName")
    fun fragmentOnStart() = this.d("Fragment OnStart : $fragmentName")
    fun fragmentOnResume() = this.d("Fragment OnResume : $fragmentName")
    fun fragmentOnPause() = this.d("Fragment OnPause : $fragmentName")
    fun fragmentOnStop() = this.d("Fragment OnStop : $fragmentName")
    fun fragmentOnDestroyView() = this.d("Fragment OnDestroyView : $fragmentName")
    fun fragmentOnDestroy() = this.d("Fragment OnDestroy : $fragmentName")
    // endregion

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime() = SimpleDateFormat("dd_mm_yyyy-hh_mm_ss").format(Date())
}