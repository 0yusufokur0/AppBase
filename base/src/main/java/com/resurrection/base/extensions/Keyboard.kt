package com.resurrection.base.extensions


import android.app.Service
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.hideKeyboard() = hideKeyboardMain(this, currentFocus!!)
fun Fragment.hideKeyboard() = hideKeyboardMain(requireActivity(), requireActivity().currentFocus!!)
fun Context.hideKeyboard(view: View) = hideKeyboardMain(this, view)
fun Service.hideKeyboard(view: View) = hideKeyboardMain(this, view)

private fun hideKeyboardMain(context: Context, view: View): InputMethodManager {
    val inputMethodManager =
        context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    return inputMethodManager
}
