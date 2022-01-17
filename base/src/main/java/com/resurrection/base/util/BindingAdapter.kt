package com.resurrection.base.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.imageLoader
import coil.load
import coil.loadAny
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.resurrection.base.general.tryCatch

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


