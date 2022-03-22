package com.resurrection.appbase.ui.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.general.toast
import com.resurrection.base.util.startCustomAnimation
import com.resurrection.base.widget.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init(savedInstanceState: Bundle?) {

        binding.recyclerView.setGridLayoutManager(2)

        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = llm
        val adapter = PhotoAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getPhotos()
        viewModel.photos.observeData(success = {
            it?.let {
                toast(it)
                adapter.addAll(it)

            }
        }, error = {
            toast("error")
        }
        )

    }

}