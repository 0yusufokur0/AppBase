package com.resurrection.appbase


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.base.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.Fragment
import com.resurrection.appbase.ui.main.photos.PhotosFragment
import com.resurrection.base.general.toast


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainActivtyViewModel>(R.layout.activity_main,MainActivtyViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


        val newFragment: PhotosFragment = PhotosFragment()
        val transaction = supportFragmentManager.beginTransaction()


        transaction.replace(binding.frameLayout.id, newFragment)
        transaction.addToBackStack(null)

        transaction.commit()


    }

    fun<T> AppCompatActivity.dataBinder(@LayoutRes layoutRes: Int) = DataBindingUtil.setContentView(this, layoutRes) as T
}

