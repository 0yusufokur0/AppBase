package com.resurrection.appbase.ui.main.photos

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.BaseAdapter
import com.resurrection.base.core.BaseFragment
import com.resurrection.appbase.data.model.photos.PhotoModelItem

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


        var adapter = BaseAdapter(R.layout.photo_item, BR.photoItem, arrayListOf<PhotoModelItem>()){

        }

        binding.appButton.setOnClickListener {
            adapter.filterBy("harum"){ it.title }
            loadingIndicator.show()
        }

        viewModel.getPhotos()
        viewModel.photos.observeData(success = {
            it?.let {
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                adapter.addAll(it)
                binding.recyclerView.adapter = adapter

            }
        }
        )
    }

}