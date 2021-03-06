package com.resurrection.appbase.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.appbase.ui.photo.PhotosFragment
import com.resurrection.base.core.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(
    R.layout.activity_main,
    MainActivityViewModel::class.java
) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init(savedInstanceState: Bundle?) {

        val fragment = PhotosFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

