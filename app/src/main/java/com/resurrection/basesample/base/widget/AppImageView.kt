package com.resurrection.basesample.base.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.resurrection.basesample.R
import com.resurrection.basesample.base.general.tryCatch
import com.resurrection.basesample.base.util.loadImage
import kotlin.math.round

class AppImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {

    init {
        this.loadImage(R.drawable.ic_launcher_background)

    }

}