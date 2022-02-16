package com.resurrection.base.core

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.resurrection.base.component.*
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>
) : AppCompatActivity(){

    @Inject lateinit var appState: AppState
    @Inject lateinit var dataHolder: DataHolderManager
    @Inject lateinit var sharedPreferences: SharedPreferencesManager
    @Inject lateinit var logger: Logger
    @Inject lateinit var loadingIndicator: AppLoadingIndicator
    @Inject lateinit var networkManager: NetworkManager

    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass] }
    lateinit var binding: VDB

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingIndicator.setUpLoadingIndicator(this)

        logger.activityOnCreate()
        logger.activityName = this@BaseActivity.localClassName

        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutRes)
        init(savedInstanceState)

    }

    @SuppressLint("ObsoleteSdkInt")
    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    //region Lifecycle
    override fun onStart() {
        super.onStart()
        appState.isAppForeground = true
        logger.activityOnStart()

    }

    override fun onResume() {
        super.onResume()
        logger.activityOnResume()
    }

    override fun onPause() {
        super.onPause()
        logger.activityOnPause()
    }

    override fun onStop() {
        super.onStop()
        appState.isAppForeground = false
        logger.activityOnStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.activityOnDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        logger.activityOnRestart()
    }
    //endregion
}