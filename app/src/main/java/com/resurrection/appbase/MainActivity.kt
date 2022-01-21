package com.resurrection.appbase

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.resurrection.appbase.ui.main.photos.PhotosFragment
import com.resurrection.base.util.Resource
import com.resurrection.base.util.isValid

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainActivityViewModel>(R.layout.activity_main,MainActivityViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {

        val fragment = PhotosFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(binding.frameLayout.id, fragment)
        transaction.addToBackStack(null)

        transaction.commit()

    }

    fun <T> AppCompatActivity.dataBinder(@LayoutRes layoutRes: Int) = DataBindingUtil.setContentView(this, layoutRes) as T
}

