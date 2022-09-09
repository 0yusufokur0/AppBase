package com.resurrection.base.extensions

import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.resurrection.base.utils.checkAreNotNull

inline fun Activity.handlePermission(
    permission: String,
    grantedListener: () -> Unit = { },
    deniedListener: () -> Unit = { },
    permanentlyDeniedListener: () -> Unit = { }
) = with(this) {

    when {
        checkSelfPermission(this, permission) == PERMISSION_GRANTED -> { // granted
            grantedListener.invoke()
        }
        shouldShowRequestPermissionRationale(this, permission) -> { // denied
            deniedListener.invoke()
        }
        else -> { // permanently denied
            permanentlyDeniedListener.invoke()
        }
    }
}

inline fun Activity.handlePermission(
    permissionRequestCode: Int? = null,
    resultRequestCode: Int? = null,
    permission: String,
    grantedListener: () -> Unit = { },
    deniedListener: () -> Unit = { },
    permanentlyDeniedListener: () -> Unit = { }
) = with(this) {

    if (checkAreNotNull(permissionRequestCode, resultRequestCode)) {
        if (permissionRequestCode == resultRequestCode) {
            handlePermission(permission, grantedListener, deniedListener, permanentlyDeniedListener)
        } else {
            return@with
        }
    } else {
        handlePermission(permission, grantedListener, deniedListener, permanentlyDeniedListener)
    }
}

inline fun Fragment.handlePermission(
    permissionRequestCode: Int? = null,
    resultRequestCode: Int? = null,
    permission: String,
    grantedListener: () -> Unit = { },
    deniedListener: () -> Unit = { },
    permanentlyDeniedListener: () -> Unit = { }
) = with(requireActivity()) {
    handlePermission(
        permissionRequestCode,
        resultRequestCode,
        permission,
        grantedListener,
        deniedListener,
        permanentlyDeniedListener
    )
}

inline fun Fragment.handlePermission(
    permission: String,
    grantedListener: () -> Unit = { },
    deniedListener: () -> Unit = { },
    permanentlyDeniedListener: () -> Unit = { }
) = with(requireActivity()) {
    handlePermission(permission, grantedListener, deniedListener, permanentlyDeniedListener)
}