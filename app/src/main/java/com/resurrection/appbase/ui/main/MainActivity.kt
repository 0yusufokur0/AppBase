package com.resurrection.appbase.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.resurrection.appbase.App
import com.resurrection.appbase.BuildConfig
import com.resurrection.appbase.ui.photo.PhotosFragment
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.core.BaseActivity
import com.resurrection.base.general.toast
import com.resurrection.base.general.tryCatch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

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
}

