package com.resurrection.base.components.widget.alertdialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertDialogManagerImpl(private val context: Context) : AlertDialogManager {

    private lateinit var alertDialog: AlertDialog

    override fun show(
        title: String,
        message: String,
        positiveButtonTitle: String,
        positiveButtonClickListener: (() -> Unit)?,
        negativeButtonTitle: String,
        negativeButtonClickListener: (() -> Unit)?,
        neutralButtonTitle: String,
        neutralButtonClickListener: (() -> Unit)?
    ): AlertDialog {
        alertDialog = MaterialAlertDialogBuilder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButtonTitle) { _, _ ->
                positiveButtonClickListener?.invoke() ?: run(alertDialog::dismiss)
            }
            setNegativeButton(negativeButtonTitle) { _, _ ->
                negativeButtonClickListener?.invoke() ?: run(alertDialog::dismiss)
            }
            setNeutralButton(neutralButtonTitle) { _, _ ->
                neutralButtonClickListener?.invoke() ?: run(alertDialog::dismiss)
            }
            setCancelable(false)
        }.create()

        with(alertDialog) {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
            show()
        }
        return alertDialog
    }

    override fun show(builder: (MaterialAlertDialogBuilder) -> Unit): AlertDialog {
        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
        builder.invoke(alertDialogBuilder)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        return alertDialog
    }
}