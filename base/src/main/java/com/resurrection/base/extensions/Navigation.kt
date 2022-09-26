package com.resurrection.base.extensions

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.navController(@IdRes id: Int): Lazy<NavController> =
    lazy { (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController }

fun Activity.navController(@IdRes id: Int): Lazy<NavController> =
    lazy { findNavController(id) }

