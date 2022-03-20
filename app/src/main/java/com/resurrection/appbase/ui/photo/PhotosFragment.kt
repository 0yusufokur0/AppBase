package com.resurrection.appbase.ui.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.util.startCustomAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init(savedInstanceState: Bundle?) {

        binding.buttonAddPhoto.setOnClickListener {

        }
    }

}