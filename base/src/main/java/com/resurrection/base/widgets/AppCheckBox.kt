package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox
import com.resurrection.base.R

class AppCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.checkboxStyle
) : MaterialCheckBox(context, attrs, defStyleAttr)