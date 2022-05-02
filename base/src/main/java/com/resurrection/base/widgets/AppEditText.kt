package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.resurrection.base.R

class AppEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {
    init {
        setBackgroundResource(R.drawable.rounded_view_bg)
        setPadding(50, 43, 50, 43)
    }
}