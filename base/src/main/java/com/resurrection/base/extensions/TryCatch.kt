package com.resurrection.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun tryCatch(func: () -> Unit) {
    try {
        func()
    } catch (e: Exception) {
        Throwable(e.toString())
    }
}

@JvmName("tryCatch")
fun <T> LifecycleOwner.tryCatch(
    defaultValue: T? = null,
    func: suspend CoroutineScope.() -> T?
): T? {
    try {
        var result: T? = null
        this.lifecycleScope.launch { result = func() }
        return result
    } catch (e: Exception) {
        Throwable(e.toString())
    }
    return null
}