package com.resurrection.base.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.resurrection.base.R
import com.resurrection.base.util.loadImage

class AppImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {

    init {
        this.loadImage(R.drawable.ic_launcher_background)
    }

}