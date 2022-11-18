package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.datastore.DataStoreManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.widget.alertdialog.AlertDialogManager
import com.resurrection.base.components.widget.loadingindicator.LoadingIndicator
import com.resurrection.base.extensions.observeData
import com.resurrection.base.utils.Resource
import javax.inject.Inject

abstract class CoreFragment @ContentView constructor(@LayoutRes val layoutRes: Int) :
    Fragment(layoutRes) {

    // region components
    @Inject
    lateinit var appState: AppState

    @Inject
    lateinit var dataHolder: DataHolderManager

    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var loggerManager: LoggerManager

    @Inject
    lateinit var loadingIndicator: LoadingIndicator

    @Inject
    lateinit var alertDialogManager: AlertDialogManager
    // endregion

    abstract fun init(view: View, savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view, savedInstanceState)
    }

    inline fun <T> LiveData<Resource<T>>.observeData(
        crossinline success: (T?) -> Unit = { },
        crossinline loading: () -> Unit = { },
        crossinline error: (Throwable?) -> Unit = { }
    ) = this.observeData(this@CoreFragment, success, loading, error)

    // endregion
}