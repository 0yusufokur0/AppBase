package com.resurrection.base.utils

import android.annotation.SuppressLint

object DateTimeUtils {

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateTime(pattern:String = "dd-MM-yy HH:mm:ss"):String{
        val date = java.util.Date()
        val sdf = java.text.SimpleDateFormat(pattern)
        return sdf.format(date)
    }

}