package com.resurrection.appbase.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.resurrection.appbase.ui.photo.PhotosFragment
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(
    R.layout.activity_main,
    MainActivityViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {

        val fragment = PhotosFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(binding.frameLayout.id, fragment)
        transaction.addToBackStack(null)

        transaction.commit()

    }

    fun <T> AppCompatActivity.dataBinder(@LayoutRes layoutRes: Int) = DataBindingUtil.setContentView(this, layoutRes) as T
}

