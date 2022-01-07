package com.resurrection.basesample.base.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.resurrection.basesample.R

class AppEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {
    init {
        setBackgroundResource(R.drawable.rounded_text_bg)
        setPadding(50, 43, 50, 43)
    }
}