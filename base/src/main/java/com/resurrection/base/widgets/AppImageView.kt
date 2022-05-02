package com.resurrection.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.resurrection.base.R


class AppImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr){
    init {
        setBackgroundResource(R.drawable.rounded_view_bg)
    }
}