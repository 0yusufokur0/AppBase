package com.resurrection.base.core

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.resurrection.base.AppSession
import com.resurrection.base.databinding.ProgressBarLayoutBinding
import javax.inject.Inject


abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel>
    (
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>
) : AppCompatActivity() {

    @Inject
    lateinit var appSession: AppSession
    lateinit var binding: VDB
    protected val viewModel by lazy { ViewModelProvider(this).get(viewModelClass) }
    private var activityName = ""

    private lateinit var loadingIndicator: AlertDialog

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            activityName = this@BaseActivity.localClassName
            loadingIndicator = setUpLoadingIndicator()
            appSession.logger.activityOnCreate(this@BaseActivity.localClassName)
            binding = DataBindingUtil.setContentView(this@BaseActivity, layoutRes)
            init(savedInstanceState)
    }

    private fun setUpLoadingIndicator(): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(this)
        val alertBinding =  ProgressBarLayoutBinding.inflate(LayoutInflater.from(this.applicationContext))
        dialogBuilder.setView(alertBinding.root)
        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        return alertDialog
    }

    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    //region Lifecycle
    override fun onStart() {
        super.onStart()
        appSession.appState.isAppForeground = true
        appSession.logger.activityOnStart(activityName)

    }

    override fun onResume() {
        super.onResume()
        appSession.logger.activityOnResume(activityName)
    }

    override fun onPause() {
        super.onPause()
        appSession.logger.activityOnPause(activityName)
    }

    override fun onStop() {
        super.onStop()
        appSession.appState.isAppForeground = false
        appSession.logger.activityOnStop(activityName)
    }

    override fun onDestroy() {
        super.onDestroy()
        appSession.logger.activityOnDestroy(activityName)
    }

    override fun onRestart() {
        super.onRestart()
        appSession.logger.activityOnRestart(activityName)
    }
    //endregion
}