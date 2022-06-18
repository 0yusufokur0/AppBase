package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.resurrection.base.R

class AppTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : MaterialTextView(context, attrs, defStyleAttr) {
    init {
        setBackgroundResource(R.drawable.rounded_view_bg)
        setPadding(50, 43, 50, 43)
        textSize = 18f
    }
}