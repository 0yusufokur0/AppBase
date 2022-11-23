package com.resurrection.base.core.activity

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.resurrection.base.extensions.observeData
import com.resurrection.base.utils.Resource

abstract class CoreActivity @ContentView constructor(@LayoutRes val layoutRes: Int) : AppCompatActivity(layoutRes) {

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    inline fun <T> LiveData<Resource<T>>.observeData(
        crossinline success: (T?) -> Unit = { },
        crossinline loading: () -> Unit = { },
        crossinline error: (Throwable?) -> Unit = { }
    ) = observeData(this@CoreActivity, success, loading, error)
}
