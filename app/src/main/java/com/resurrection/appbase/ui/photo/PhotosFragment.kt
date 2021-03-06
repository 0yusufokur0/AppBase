package com.resurrection.appbase.ui.photo

import android.os.Bundle
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


    }
}
