package com.resurrection.basesample.base.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.resurrection.basesample.R
import com.resurrection.basesample.base.util.loadImage

class AppImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {

    init {
        this.loadImage(R.drawable.ic_launcher_background)
    }

}