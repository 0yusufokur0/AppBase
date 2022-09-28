package com.resurrection.base.core.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import com.resurrection.base.utils.fragment.FragmentLifecycleEvent
import com.resurrection.base.utils.fragment.FragmentLifecycleEventObserver

abstract class LifecycleFragment @ContentView constructor(@LayoutRes layoutRes: Int) : CoreFragment(layoutRes), FragmentLifecycleEventObserver {

    abstract override fun init(view: View, savedInstanceState: Bundle?)

    private val lifecycleObservers = mutableListOf<FragmentLifecycleEventObserver>()

    fun addLifecycleObserver(observer:FragmentLifecycleEventObserver){
        lifecycleObservers.add(observer)
    }
    fun removeLifecycleObserver(observer:FragmentLifecycleEventObserver){
        lifecycleObservers.remove(observer)
    }

    fun removeAllLifecycleObserver(){
        lifecycleObservers.clear()
    }

    @CallSuper
    override fun onStateChanged(owner: LifecycleOwner?, event: FragmentLifecycleEvent) {
        lifecycleObservers.forEach {
            it.onStateChanged(owner, event)
        }

    }

    // region Lifecycle Event
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onStateChanged(null,FragmentLifecycleEvent.ON_ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStateChanged(null,FragmentLifecycleEvent.ON_CREATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onStateChanged(null,FragmentLifecycleEvent.ON_CREATE_VIEW)
        return callOnCreateViewSuper(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callOnViewCreatedSuper(view, savedInstanceState)
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_VIEW_CREATED)
        init(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_VIEW_STATE_RESTORED)
    }

    override fun onStart() {
        super.onStart()
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_START)
    }

    override fun onResume() {
        super.onResume()
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_STOP)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        onStateChanged(viewLifecycleOwner,FragmentLifecycleEvent.ON_SAVE_INSTANCE_STATE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onStateChanged(null,FragmentLifecycleEvent.ON_DESTROY_VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        onStateChanged(null,FragmentLifecycleEvent.ON_DESTROY)
    }

    override fun onDetach() {
        super.onDetach()
        onStateChanged(null,FragmentLifecycleEvent.ON_DETACH)
    }
    // endregion
}