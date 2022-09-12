package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.utils.FragmentLifecycleEvent

abstract class LifecycleFragment @ContentView constructor(@LayoutRes layoutRes : Int) : CoreFragment(layoutRes),
    LifecycleObserver {

    abstract override fun init(view: View, savedInstanceState: Bundle?)



/*
    open fun onStateChanged(event: FragmentLifecycleEvent) {
        when (event) {
            FragmentLifecycleEvent.ON_CREATE -> {
                loggerManager.fragmentOnCreate()
            }
            FragmentLifecycleEvent.ON_CREATE_VIEW -> {
                loggerManager.fragmentOnCreateView()
            }
            FragmentLifecycleEvent.ON_VIEW_CREATED -> {
                loggerManager.initFragment(this.javaClass.simpleName)
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
*/

/*    // region Lifecycle Event
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStateChanged(FragmentLifecycleEvent.ON_CREATE)

        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onStateChanged(FragmentLifecycleEvent.ON_CREATE_VIEW)
        return callOnCreateViewSuper(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callOnViewCreatedSuper(view, savedInstanceState)
        onStateChanged(FragmentLifecycleEvent.ON_VIEW_CREATED)
        init(view, savedInstanceState)
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
    // endregion*/
}