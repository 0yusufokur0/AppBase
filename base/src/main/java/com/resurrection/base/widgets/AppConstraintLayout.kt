package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.resurrection.base.R

class AppConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :ConstraintLayout(context, attrs, defStyleAttr){
    init {
        setBackgroundResource(R.drawable.rounded_view_bg)
    }
}