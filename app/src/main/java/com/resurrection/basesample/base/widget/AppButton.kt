package com.resurrection.basesample.base.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import com.google.android.material.button.MaterialButton
import com.resurrection.basesample.R
import com.resurrection.basesample.base.util.startCustomAnimation

@SuppressLint("ClickableViewAccessibility")
class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialButtonStyle
) : MaterialButton(context, attrs, defStyleAttr){

    init {
        setBackgroundResource(R.drawable.rounded_text_bg)

        this.setOnTouchListener { v, event ->
            when (event.action) {
                ACTION_DOWN -> background.alpha = 154
                ACTION_UP -> {
                    startCustomAnimation(R.anim.button_click)
                    background.alpha = 255
                }
            }
            false
        }
    }
}


