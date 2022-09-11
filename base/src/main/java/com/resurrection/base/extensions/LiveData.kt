package com.resurrection.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.Status


inline fun <T> LiveData<Resource<T>>.observeData(
    lifecycleOwner: LifecycleOwner,
    crossinline success: (T?) -> Unit = { },
    crossinline loading: () -> Unit = { },
    crossinline error: (Throwable?) -> Unit = { }
) {
    this.observe(lifecycleOwner) { resource ->
        when (resource.status) {
            Status.SUCCESS -> success.invoke(resource.data)
            Status.LOADING -> loading.invoke()
            Status.ERROR -> error.invoke(resource.error)
        }
    }
}