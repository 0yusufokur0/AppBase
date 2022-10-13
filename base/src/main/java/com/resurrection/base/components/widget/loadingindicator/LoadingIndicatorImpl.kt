package com.resurrection.base.components.widget.loadingindicator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.resurrection.base.databinding.ProgressBarLayoutBinding

class LoadingIndicatorImpl(
    private val context: Context,
    private val initializer: (LayoutInflater) -> ViewBinding = ProgressBarLayoutBinding::inflate
) : LoadingIndicator {

    private lateinit var alertDialog: AlertDialog
    var isShowing = false; private set

    private var alertDialogBinding: ViewBinding = initializer.invoke(LayoutInflater.from(context))

    init {
        createAlertDialog()
    }

   private fun createAlertDialog() {
        alertDialog = MaterialAlertDialogBuilder(context).apply {
            setCancelable(false)
            setView(alertDialogBinding.root)
        }.create()

        with(alertDialog) {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }
    }

    override fun show() {
        alertDialog.show()
        isShowing = true
    }

    override fun hide() {
        alertDialog.hide()
        isShowing = false
    }
}