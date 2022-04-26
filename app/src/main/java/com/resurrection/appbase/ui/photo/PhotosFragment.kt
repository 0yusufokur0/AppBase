package com.resurrection.appbase.ui.photo

import android.os.Bundle
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.component.DataStoreManager
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.general.toast
import com.resurrection.base.widget.init
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

        val dataStore = DataStoreManager(requireContext())

        dataStore.getInt(
            key = "intKey",
            success = {
                requireActivity().runOnUiThread {
                    toast(it)
                }
            }, error = {
                requireActivity().runOnUiThread {
                    toast("hata verdi")
                }
            }
        )

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