package com.resurrection.base.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.resurrection.base.databinding.ProgressBarLayoutBinding

class AppLoadingIndicator {

    private lateinit var context: Context
    private lateinit var alertDialog: AlertDialog
    var isShowing = false
        private set


    fun setUpLoadingIndicator(context: Context) {
        if (!this::context.isInitialized){
            this.context = context
            val dialogBuilder = AlertDialog.Builder(context)
            val alertBinding =  ProgressBarLayoutBinding.inflate(LayoutInflater.from(context.applicationContext))
            dialogBuilder.setView(alertBinding.root)
            alertDialog = dialogBuilder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setCanceledOnTouchOutside(false)
            alertDialog.hide()
        }
    }

    fun show(){
        if (this::alertDialog.isInitialized){
            alertDialog.show()
            isShowing = true
        }
    }

    fun hide(){
        if (this::alertDialog.isInitialized){
            alertDialog.hide()
            isShowing = false
        }
    }


}