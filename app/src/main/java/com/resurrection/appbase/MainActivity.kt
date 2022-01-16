package com.resurrection.appbase

import android.annotation.SuppressLint

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.resurrection.appbase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.widget.RelativeLayout

import android.widget.ProgressBar


import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import com.resurrection.base.core.BaseActivity

import com.resurrection.base.databinding.ProgressBarLayoutBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainActivtyViewModel>(R.layout.activity_main,MainActivtyViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


    }

    fun<T> AppCompatActivity.dataBinder(@LayoutRes layoutRes: Int) = DataBindingUtil.setContentView(this, layoutRes) as T
}

