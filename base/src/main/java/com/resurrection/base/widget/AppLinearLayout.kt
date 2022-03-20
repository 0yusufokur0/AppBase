package com.resurrection.base.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.resurrection.base.R

@SuppressLint("ClickableViewAccessibility")
class AppLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :LinearLayoutCompat(context, attrs, defStyleAttr) {
    init {
        setBackgroundResource(R.drawable.rounded_view_bg)
    }
}