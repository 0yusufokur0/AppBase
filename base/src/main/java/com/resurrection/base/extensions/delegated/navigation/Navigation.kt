package com.resurrection.base.extensions.delegated.navigation

import android.app.Activity
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.resurrection.base.components.lifecycle.activity.ActivityLifecycleAwareLazyComponent
import com.resurrection.base.core.activity.LifecycleActivity

fun LifecycleActivity.navController(@IdRes id: Int) = ActivityLifecycleAwareLazyComponent(
    lifecycleActivity = this,
    instanceCreator = { (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController }
)

fun Activity.navController(@IdRes id: Int): Lazy<NavController> =
    lazy { findNavController(id) }

