package com.resurrection.appbase.ui.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.util.startCustomAnimation
import com.resurrection.base.widget.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init(savedInstanceState: Bundle?) {

        var adapter = BaseAdapter(R.layout.photo_item, BR.photoItem, arrayListOf<PhotoModelItem>()){

        }

        viewModel.getPhotos()
        viewModel.photos.observeData(success = {
            it?.let {
                binding.recyclerView.setGridLayoutManager(2)
                adapter.addAll(it)
                binding.recyclerView.adapter = adapter
            }
        }
        )

    }

}