package com.resurrection.appbase.ui.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
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
import com.resurrection.base.widget.init
import com.resurrection.base.widget.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    private val photoAdapter = PhotoAdapter()

    override fun init(savedInstanceState: Bundle?) {
        initPhotoRecyclerView()
        initPhotoAdapterOnItemClick()
        initPhotosObserver()
        getPhotos()
    }

    private fun initPhotoRecyclerView(){
        binding.photoRecyclerView.init(photoAdapter)
    }

    private fun initPhotoAdapterOnItemClick(){
        photoAdapter.onItemClick = {
            toast(it.title)
        }
    }

    private fun initPhotosObserver(){
        viewModel.photos.observeData(success = {
            it?.let {
                toast(it)
                photoAdapter.addAll(it)

            }
        }, error = {
            toast("error")
        }
        )
    }
    private fun getPhotos(){
        viewModel.getPhotos()
    }
}