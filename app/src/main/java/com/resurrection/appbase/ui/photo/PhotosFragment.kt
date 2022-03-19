package com.resurrection.appbase.ui.photo

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.adapter.BaseAdapter
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.general.toast
import com.resurrection.base.widget.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


        binding.recyclerView.setGridLayoutManager(2)

        val adapter = BaseAdapter(R.layout.photo_item, BR.photoItem, arrayListOf<PhotoModelItem>()){

        }


        binding.appButton.setOnClickListener {
            adapter.filterBy("harum"){ it.title }
            loadingIndicator.show()
        }

        viewModel.getPhotos()
        viewModel.photos.observeData(success = {
            it?.let {

                adapter.addAll(it)
                binding.recyclerView.adapter = adapter

            }
        }
        )
    }


}