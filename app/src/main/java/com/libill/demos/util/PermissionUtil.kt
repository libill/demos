package com.libill.demos.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.coroutines.sendSuspend
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.isDenied
import com.fondesa.kpermissions.request.PermissionRequest
import com.libill.demos.R

private const val TAG = "PermissionUtil"

suspend fun FragmentActivity.getReadPhoneStatePermission(tipMessage: String = getString(R.string.permission_request_tip)): Boolean {
    val result = getReadPhoneStatePermission(this).sendSuspend()
    return showSettingDialog(result, tipMessage)
}

suspend fun FragmentActivity.requestReadContactsPermission(tipMessage: String = getString(R.string.permission_request_tip)): Boolean {
    val result = getReadContactsPermission(this).sendSuspend()
    return showSettingDialog(result, tipMessage)
}

suspend fun FragmentActivity.requestAudioPermission(tipMessage: String = getString(R.string.permission_request_tip)): Boolean {
    val result = getAudioPermission(this).sendSuspend()
    return showSettingDialog(result, tipMessage)
}

private fun FragmentActivity.showSettingDialog(
    result: List<PermissionStatus>,
    msg: String,
    showCancel: Boolean = false,
): Boolean {
    Log.i(TAG, "permission result: $result")
    result.forEach { permission ->
        if(permission.isDenied()) {
            showGoToSettingDialog(this, msg, showCancel)
            return false
        }
    }
    return true
}

private fun getAudioPermission(activity: FragmentActivity): PermissionRequest {
    return activity.permissionsBuilder(
        Manifest.permission.RECORD_AUDIO
    ).build()
}

private fun getReadContactsPermission(activity: FragmentActivity): PermissionRequest {
    return activity.permissionsBuilder(
        Manifest.permission.READ_CONTACTS
    ).build()
}

private fun getReadPhoneStatePermission(activity: FragmentActivity): PermissionRequest {
    return activity.permissionsBuilder(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_SMS,
        Manifest.permission.READ_PHONE_NUMBERS,
    ).build()
}

private fun getAudioCameraPermission(activity: FragmentActivity): PermissionRequest {
    val request =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activity.permissionsBuilder(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH_CONNECT
            ).build()
        } else {
            activity.permissionsBuilder(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).build()
        }
    return request
}


suspend fun Fragment.requestAudioCameraPermission(tipMessage: String): Boolean {
    val result = getAudioCameraPermission(this).sendSuspend()
    return showSettingDialog(result, tipMessage)
}

private fun Fragment.showSettingDialog(
    result: List<PermissionStatus>,
    msg: String,
    showCancel: Boolean = false,
): Boolean {
    Log.i(TAG, "permission result: $result")
    result.forEach { permission ->
        if (permission.isDenied()) {
            activity?.let {
                if (it.isActivityRunning()) {
                    showGoToSettingDialog(it, msg, showCancel)
                }
            }
            return false
        }
    }
    return true
}

private fun getAudioCameraPermission(activity: Fragment): PermissionRequest {
    val request =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activity.permissionsBuilder(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH_CONNECT
            ).build()
        } else {
            activity.permissionsBuilder(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).build()
        }
    return request
}

suspend fun FragmentActivity.requestNotificationPermission(): Boolean {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val result =
            permissionsBuilder(Manifest.permission.POST_NOTIFICATIONS).build().sendSuspend()
        checkPermissionResult(result)
    } else {
        true
    }
}

private fun checkPermissionResult(result: List<PermissionStatus>): Boolean {
    Log.i(TAG, "permission result: $result")
    result.forEach { permission ->
        if(permission.isDenied()) {
            return false
        }
    }
    return true
}

private fun showGoToSettingDialog(
    activity: Activity,
    msg: String,
    showCancel: Boolean,
): Boolean {
    openSystemSettings(activity)
    return true
}

fun openSystemSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    try {
        context.startActivity(intent)
    } catch (e: Throwable) {
        Log.e(TAG, "Open system settings error", e)
    }
}