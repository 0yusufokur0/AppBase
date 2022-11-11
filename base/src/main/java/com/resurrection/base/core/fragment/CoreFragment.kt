package com.resurrection.base.core.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
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
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver
import com.resurrection.base.utils.getPrivatePropertyValueByIndex
import javax.inject.Inject

abstract class CoreFragment @ContentView constructor(@LayoutRes val layoutRes: Int) :
    Fragment(layoutRes), FragmentLifecycleEventObserver {

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

    fun getLayoutId() = getPrivatePropertyValueByIndex(9)

    private val lifecycleObservers = mutableListOf<FragmentLifecycleEventObserver>()

    fun addLifecycleObserver(observer: FragmentLifecycleEventObserver) = lifecycleObservers.add(observer)

    fun removeLifecycleObserver(observer: FragmentLifecycleEventObserver) = lifecycleObservers.remove(observer)

    fun removeAllLifecycleObserver() = lifecycleObservers.clear()

    inline fun <T> LiveData<Resource<T>>.observeData(
        crossinline success: (T?) -> Unit = { },
        crossinline loading: () -> Unit = { },
        crossinline error: (Throwable?) -> Unit = { }
    ) = this.observeData(this@CoreFragment, success, loading, error)

    // region Lifecycle Event
    @CallSuper
    override fun onStateChanged(owner: LifecycleOwner?, event: FragmentLifecycleEvent) {
        lifecycleObservers.forEach {
            it.onStateChanged(owner, event)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onStateChanged(null, FragmentLifecycleEvent.ON_ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStateChanged(null, FragmentLifecycleEvent.ON_CREATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onStateChanged(null, FragmentLifecycleEvent.ON_CREATE_VIEW)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_VIEW_CREATED)
        init(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_VIEW_STATE_RESTORED)
    }

    override fun onStart() {
        super.onStart()
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_START)
    }

    override fun onResume() {
        super.onResume()
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_STOP)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        onStateChanged(viewLifecycleOwner, FragmentLifecycleEvent.ON_SAVE_INSTANCE_STATE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onStateChanged(null, FragmentLifecycleEvent.ON_DESTROY_VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        onStateChanged(null, FragmentLifecycleEvent.ON_DESTROY)
    }

    override fun onDetach() {
        super.onDetach()
        onStateChanged(null, FragmentLifecycleEvent.ON_DETACH)
    }
    // endregion
}