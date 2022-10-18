package com.resurrection.base.components.widget.alertdialog

import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

interface AlertDialogManager {

    fun show(
        title: String,
        message: String,
        positiveButtonTitle: String,
        positiveButtonClickListener: (() -> Unit)? = null,
        negativeButtonTitle: String,
        negativeButtonClickListener: (() -> Unit)? = null,
        neutralButtonTitle: String,
        neutralButtonClickListener: (() -> Unit)? = null
    ): AlertDialog

    fun show(builder:(MaterialAlertDialogBuilder)->Unit): AlertDialog
}
