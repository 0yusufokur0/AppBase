package com.resurrection.base.util


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.resurrection.base.R
import com.resurrection.base.databinding.ProgressBarLayoutBinding


fun setUpLoadingIndicator(context: Context): AlertDialog {
    val dialogBuilder = AlertDialog.Builder(context)
    val alertBinding =  ProgressBarLayoutBinding.inflate(LayoutInflater.from(context.applicationContext))
    dialogBuilder.setView(alertBinding.root)
    val alertDialog = dialogBuilder.create()
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alertDialog.setCanceledOnTouchOutside(false)
    alertDialog.hide()
    return alertDialog
}

fun isNetworkAvailable(context: Context): Boolean {
    try {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> true
            }
        }else false
    }catch (e:Exception){
        return false
    }
}

fun Any.isValid(): Boolean {
    var isValid = true
    val fields = this.javaClass.declaredFields

    fields.forEachIndexed { i, field ->

        fields[i].isAccessible = true

        val value = fields[i].get(this)

        Log.w("Msg", "Value of Field " + fields[i].name + " is " + value)

        if (value == 0 || value == 0.0 || value == "" || value == null) {
            isValid = false
        }
    }
    return isValid
}

fun View.startCustomAnimation(anim: Int) = this.startAnimation(AnimationUtils.loadAnimation(this.context, anim))

fun AppCompatActivity.changeStatusBarColor(color: Int = android.R.color.black) {
    val window: Window = this.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, color)
}