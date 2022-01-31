package com.resurrection.base.general

import android.app.Service
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.Exception

fun AppCompatActivity.toast(message: String) = toast(this, message, Toast.LENGTH_SHORT)
fun Fragment.toast(message: String) = toast(requireContext(), message, Toast.LENGTH_SHORT)
fun Context.toast(message: String) = toast(this, message, Toast.LENGTH_SHORT)
fun Service.toast(message: String) = toast(this, message, Toast.LENGTH_SHORT)
fun AppCompatActivity.toastLong(message: String) = toast(this, message, Toast.LENGTH_LONG)
fun Fragment.toastLong(message: String) = toast(requireContext(), message, Toast.LENGTH_LONG)
fun Context.toastLong(message: String) = toast(this, message, Toast.LENGTH_LONG)
fun Service.toastLong(message: String) = toast(this, message, Toast.LENGTH_LONG)

fun AppCompatActivity.toast(message: Int) = toast(this, message, Toast.LENGTH_SHORT)
fun Fragment.toast(message: Int) = toast(requireContext(), message, Toast.LENGTH_SHORT)
fun Context.toast(message: Int) = toast(this, message, Toast.LENGTH_SHORT)
fun Service.toast(message: Int) = toast(this, message, Toast.LENGTH_SHORT)
fun AppCompatActivity.toastLong(message: Int) = toast(this, message, Toast.LENGTH_LONG)
fun Fragment.toastLong(message: Int) = toast(requireContext(), message, Toast.LENGTH_LONG)
fun Context.toastLong(message: Int) = toast(this, message, Toast.LENGTH_LONG)
fun Service.toastLong(message: Int) = toast(this, message, Toast.LENGTH_LONG)

fun AppCompatActivity.toast(message: Any) = toast(this, message, Toast.LENGTH_SHORT)
fun Fragment.toast(message: Any) = toast(requireContext(), message, Toast.LENGTH_SHORT)
fun Context.toast(message: Any) = toast(this, message, Toast.LENGTH_SHORT)
fun Service.toast(message: Any) = toast(this, message, Toast.LENGTH_SHORT)
fun AppCompatActivity.toastLong(message: Any) = toast(this, message, Toast.LENGTH_LONG)
fun Fragment.toastLong(message: Any) = toast(requireContext(), message, Toast.LENGTH_LONG)
fun Context.toastLong(message: Any) = toast(this, message, Toast.LENGTH_LONG)
fun Service.toastLong(message: Any) = toast(this, message, Toast.LENGTH_LONG)

private fun toast(context: Context,message: String, duration: Int): Toast {
    val toast = Toast.makeText(context, message, duration)
    toast.show()
    return toast
}

private fun toast(context: Context,message: Int, duration: Int): Toast {
    return try {
        val msg = context.getString(message)
        val toast = Toast.makeText(context, msg, duration)
        toast.show()
        toast
    }catch (e:Exception) {
        val toast = Toast.makeText(context, message, duration)
        toast.show()
        toast
    }
}

private fun toast(context: Context,message: Any, duration: Int): Toast {
    val toast = Toast.makeText(context, message.toString(), duration)
    toast.show()
    return toast
}