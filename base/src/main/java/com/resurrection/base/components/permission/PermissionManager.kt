package com.resurrection.base.components.permission

import androidx.appcompat.app.AppCompatActivity

interface PermissionManager {

    fun checkPermission(activity: AppCompatActivity,permission: String): Boolean

    fun requestPermission(activity: AppCompatActivity,requestCode:Int,permission: String)

    fun handlePermission(
        activity: AppCompatActivity,
        permission: String,
        grantedListener: (String) -> Unit = { },
        deniedListener: (String) -> Unit = { },
        permanentlyDeniedListener: (String) -> Unit = { }
    )

    fun openAppSettingWithResult(activity: AppCompatActivity, requestCode: Int)
}