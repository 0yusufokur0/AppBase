package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class CoreFragment : Fragment()  {

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


    open fun onStateChanged(event: FragmentLifecycleEvent) {
    }
}