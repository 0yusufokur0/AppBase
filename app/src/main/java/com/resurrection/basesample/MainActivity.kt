package com.resurrection.basesample

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.resurrection.base.general.toast
import com.resurrection.basesample.databinding.ActivityMainBinding


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        toast("sdlfgk")

    }
}

