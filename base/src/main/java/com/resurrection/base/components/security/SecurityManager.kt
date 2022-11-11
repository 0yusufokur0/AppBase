package com.resurrection.base.components.security

import android.content.Context
import com.scottyab.rootbeer.RootBeer

class SecurityManager(private val context: Context) {

    fun isRooted() = RootBeer(context).isRooted
}