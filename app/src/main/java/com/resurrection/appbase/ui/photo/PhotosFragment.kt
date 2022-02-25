package com.resurrection.appbase.ui.photo

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.BaseAdapter
import com.resurrection.base.core.BaseFragment
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.base.component.Cryptography
import com.resurrection.base.general.toast
import com.resurrection.base.widget.setGridLayoutManager
import com.resurrection.base.widget.setHorizontalLinearLayoutManager
import com.resurrection.base.widget.setVerticalLinearLayoutManager

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {


        val pass = "12345678"
        val text = "aaa"
        val encrypted = Cryptography.encrypt(pass, text)
        println(encrypted)
        val decrypted = Cryptography.decrypt(pass, encrypted)
        println(decrypted)



        var adapter = BaseAdapter(R.layout.photo_item, BR.photoItem, arrayListOf<PhotoModelItem>()){

        }

        binding.appButton.setOnClickListener {
            adapter.filterBy("harum"){ it.title }
            loadingIndicator.show()
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