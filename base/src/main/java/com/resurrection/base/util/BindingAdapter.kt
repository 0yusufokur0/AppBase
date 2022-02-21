package com.resurrection.base.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.imageLoader
import coil.loadAny
import coil.transform.RoundedCornersTransformation

@BindingAdapter("loadImage")
fun ImageView.loadImage(image: Any?) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setArrowDimensions(30f, 30f)
    circularProgressDrawable.start()

    image?.let {
        loadAny(image, this.context.imageLoader) {
            placeholder(circularProgressDrawable)
            transformations(RoundedCornersTransformation(12f))
        }
    }
}


