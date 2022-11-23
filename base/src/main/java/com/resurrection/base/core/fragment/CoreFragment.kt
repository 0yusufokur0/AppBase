package com.resurrection.base.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.resurrection.base.extensions.observeData
import com.resurrection.base.utils.Resource

abstract class CoreFragment @ContentView constructor(@LayoutRes val layoutRes: Int) :
    Fragment(layoutRes) {

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
}