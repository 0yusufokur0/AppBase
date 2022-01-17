package com.resurrection.appbase.ui.main.photos

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.BaseAdapter
import com.resurrection.base.core.BaseFragment
import com.resurrection.base.util.loadImage

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


        //binding.imageView.loadImage("https://miro.medium.com/max/700/1*BlKF-lVu3O8SSXwKPwjKsQ.png")


        viewModel.getPhotos()
        viewModel.photos.observeData(success = {
            it?.let {
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                binding.recyclerView.adapter = BaseAdapter(R.layout.photo_item, BR.photoItem, it) {

                }
            }
        })
    }
}