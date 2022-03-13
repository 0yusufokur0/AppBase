package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class LifecycleFragment : CoreFragment() {

    open fun onStateChanged(event: FragmentLifecycleEvent) {
        when (event) {
            FragmentLifecycleEvent.ON_CREATE -> {
                loggerManager.fragmentOnCreate()
            }
            FragmentLifecycleEvent.ON_CREATE_VIEW -> {
            }
            FragmentLifecycleEvent.ON_VIEW_CREATED -> {
                loggerManager.initFragment(this.javaClass.simpleName)
                appState.isNetworkAvailable = networkManager.checkNetworkAvailable()
            }
            FragmentLifecycleEvent.ON_START -> {
                loggerManager.fragmentOnStart()
            }
            FragmentLifecycleEvent.ON_RESUME -> {
                loggerManager.fragmentOnResume()

            }
            FragmentLifecycleEvent.ON_PAUSE -> {
                loggerManager.fragmentOnPause()

            }
            FragmentLifecycleEvent.ON_STOP -> {
                loggerManager.fragmentOnStop()

            }
            FragmentLifecycleEvent.ON_DESTROY_VIEW -> {
                loggerManager.fragmentOnDestroyView()
            }
            FragmentLifecycleEvent.ON_DESTROY -> {
                loggerManager.fragmentOnDestroy()
            }
        }
    }

    // region Lifecycle Event
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStateChanged(FragmentLifecycleEvent.ON_CREATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onStateChanged(FragmentLifecycleEvent.ON_CREATE_VIEW)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStateChanged(FragmentLifecycleEvent.ON_VIEW_CREATED)
    }

    override fun onStart() {
        super.onStart()
        onStateChanged(FragmentLifecycleEvent.ON_START)
    }

    override fun onResume() {
        super.onResume()
        onStateChanged(FragmentLifecycleEvent.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        onStateChanged(FragmentLifecycleEvent.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        onStateChanged(FragmentLifecycleEvent.ON_STOP)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onStateChanged(FragmentLifecycleEvent.ON_DESTROY_VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        onStateChanged(FragmentLifecycleEvent.ON_DESTROY)
    }
    // endregion
}