package com.resurrection.base.extensions

import android.view.View
import android.view.animation.AnimationUtils

fun View.startCustomAnimation(anim: Int) = this.startAnimation(AnimationUtils.loadAnimation(this.context, anim))
