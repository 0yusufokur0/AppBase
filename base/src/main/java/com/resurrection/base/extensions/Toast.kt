package com.resurrection.base.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: String?) = toast(this, message)
fun Context.toast(message: Int?) = toast(this, message)
fun Context.toast(message: Any?) = toast(this, message)

fun Context.toastLong(message: String?) = toastLong(this, message)
fun Context.toastLong(message: Int?) = toastLong(this, message)
fun Context.toastLong(message: Any?) = toastLong(this, message)

fun Fragment.toast(message: String?) = toast(requireContext(), message)
fun Fragment.toast(message: Int?) = toast(requireContext(), message)
fun Fragment.toast(message: Any?) = toast(requireContext(), message)

fun Fragment.toastLong(message: Int?) = toastLong(requireContext(), message)
fun Fragment.toastLong(message: String?) = toastLong(requireContext(), message)
fun Fragment.toastLong(message: Any?) = toastLong(requireContext(), message)


@JvmName("toastLong1")
private fun toastLong(context: Context, message: String?) = toast(context, message, Toast.LENGTH_LONG)

@JvmName("toastLongInt")
private fun toastLong(context: Context, messageId: Int?) = toast(context, messageId, Toast.LENGTH_LONG)

@JvmName("toastLongAny")
private fun toastLong(context: Context, message: Any?) = toast(context, message, Toast.LENGTH_LONG)

private fun toast(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT) = message?.let {
    Toast.makeText(context, message, duration).show()
}

private fun toast(context: Context, messageId: Int?, duration: Int = Toast.LENGTH_SHORT) = messageId?.let {
    Toast.makeText(context, messageId, duration).show()
}

private fun toast(context: Context, message: Any?, duration: Int = Toast.LENGTH_SHORT) = message?.let {
    Toast.makeText(context, message.toString(), duration).show()
}
