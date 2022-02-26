package com.resurrection.base.component

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class BiometricManager {

    private lateinit var activity: AppCompatActivity
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    fun init(activity: AppCompatActivity) {
        this.activity = activity
    }

    fun setup(
        title: String,
        subtitle: String,
        description: String,
        biometricIsAvailable: () -> Unit,
        biometricIsUnAvailable: () -> Unit,
        success: (BiometricPrompt.AuthenticationResult) -> Unit,
        error: () -> Unit
    ) {
        if (this::activity.isInitialized) {
            executor = ContextCompat.getMainExecutor(activity)

            if (isBiometricHardWareAvailable()) {
                initBiometricPrompt(title, subtitle, description)
                biometricIsAvailable()
            } else {
                if (deviceHasPasswordPinLock()) {
                    initBiometricPrompt(title, subtitle, description)
                    biometricIsAvailable()
                } else biometricIsUnAvailable()

            }
            setBiometricPrompt(activity, success, error)
        }
    }

    fun authenticate() {
        if (this::activity.isInitialized) biometricPrompt.authenticate(promptInfo)
    }

    private fun setBiometricPrompt(
        activity: AppCompatActivity,
        success: (BiometricPrompt.AuthenticationResult) -> Unit,
        error: () -> Unit
    ) {
        biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    success(result)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    error()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    error()
                }
            })
    }

    private fun initBiometricPrompt(
        title: String,
        subtitle: String,
        description: String,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val authFlag =
                BiometricManager.Authenticators.DEVICE_CREDENTIAL or BiometricManager.Authenticators.BIOMETRIC_STRONG
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setAllowedAuthenticators(authFlag)
                .build()
        } else {
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setDeviceCredentialAllowed(true)
                .build()
        }
    }

    private fun isBiometricHardWareAvailable(): Boolean {
        val biometricManager = BiometricManager.from(activity)

        val authIntKey =
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) biometricManager.canAuthenticate()
            else  biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL or BiometricManager.Authenticators.BIOMETRIC_STRONG)

        return when (authIntKey) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> false
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> false
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> true
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> true
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> false
            else -> false
        }
    }

    private fun deviceHasPasswordPinLock() =
        (activity.getSystemService(AppCompatActivity.KEYGUARD_SERVICE) as KeyguardManager).isKeyguardSecure
}
