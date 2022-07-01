package com.resurrection.base.components.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.resurrection.base.components.datastore.DataStoreManager

class PermissionManagerImpl(private val dataStoreManager: DataStoreManager) : PermissionManager {

    override fun checkPermission(activity: AppCompatActivity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(activity: AppCompatActivity, requestCode: Int, permission: String) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }


    override fun handlePermission(
        activity: AppCompatActivity,
        permission: String,
        grantedListener: (String) -> Unit,
        deniedListener: (String) -> Unit,
        permanentlyDeniedListener: (String) -> Unit
    ) {
        val dataStoreKey = permission + "isFirstPermanentlyDenied"

        fun handlePermission(isFirstPermanentlyDenied:Boolean){
            when {
                checkPermission(activity,permission) -> { // granted
                    grantedListener.invoke(permission)
                }
                ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) -> { // denied
                    deniedListener.invoke(permission)
                }
                else -> { // permanently denied
                    if (isFirstPermanentlyDenied) {
                        permanentlyDeniedListener.invoke(permission)
                    }
                    dataStoreManager.putBoolean(dataStoreKey, true)
                }
            }
        }

        dataStoreManager.getBoolean(
            key = dataStoreKey,
            defValue = false,
            success = { isFirstPermanentlyDenied ->
                handlePermission(isFirstPermanentlyDenied)
            }, error = {
                handlePermission(isFirstPermanentlyDenied = false)
            }
        )
    }

    override fun openAppSettingWithResult(activity: AppCompatActivity, requestCode: Int) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }

}