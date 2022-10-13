package com.resurrection.base.components.widget.alertdialog

import androidx.appcompat.app.AlertDialog

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
}
