package com.resurrection.base.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.resurrection.base.general.tryCatch

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(imageUrl: Any?) {
    tryCatch {
        imageUrl?.let {
            val circularProgressDrawable = CircularProgressDrawable(this.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setArrowDimensions(30f, 30f)
            circularProgressDrawable.start()
            val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(52))
            Glide.with(this)
                .load(imageUrl)/*.override(500,750)*/
                .placeholder(circularProgressDrawable)
                //.error(R.drawable.image_not_found)  // any image in case of error
                .apply(requestOptions)
                .into(this)
        }
    }
}

