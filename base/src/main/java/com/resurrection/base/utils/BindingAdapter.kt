package com.resurrection.base.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.imageLoader
import coil.loadAny
import coil.transform.RoundedCornersTransformation
import com.resurrection.base.R
import com.resurrection.base.extensions.startCustomAnimation

@BindingAdapter("loadImageWithRoundedCorner")
fun ImageView.loadImageWithRoundedCorner(image: Any?) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setArrowDimensions(30f, 30f)
    circularProgressDrawable.start()

    image?.let {
        loadAny(image, this.context.imageLoader) {
            placeholder(circularProgressDrawable)
            transformations(RoundedCornersTransformation(8f))
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("setClickAnimation")
fun View.setClickAnimation(enable: Boolean = false) {
    if (enable) {
        setOnTouchListener { v, event ->
            background.alpha = 200
            when (event.action) {
                MotionEvent.ACTION_DOWN -> background.alpha = 200
                MotionEvent.ACTION_UP -> {
                    startCustomAnimation(R.anim.button_action_down)
                    background.alpha = 255
                }
            }
            true
        }
    }
}


