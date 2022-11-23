package com.resurrection.appbase.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.ActivityMainBinding
import com.resurrection.appbase.ui.main.adapter.MultipleAdapterTest
import com.resurrection.base.core.activity.CoreActivity
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : CoreActivity(R.layout.activity_main) {
    val binding by dataBinding<ActivityMainBinding>()
    val viewModel by viewModel<MainActivityViewModel>()
    val adapter by lazy { MultipleAdapterTest() }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init(savedInstanceState: Bundle?) {

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter

        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
        adapter.add("hello world")
        adapter.add("https://images.dog.ceo/breeds/malinois/n02105162_6116.jpg")
    }
}
