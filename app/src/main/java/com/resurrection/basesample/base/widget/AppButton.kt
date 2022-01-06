package com.resurrection.basesample.base.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.marginTop
import com.google.android.material.button.MaterialButton
import com.resurrection.basesample.R

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialButtonStyle
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        setBackgroundResource(R.drawable.rounded_rectangle_bg)
    }


}