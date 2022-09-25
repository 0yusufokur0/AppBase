package com.resurrection.appbase.ui.photo

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.extensions.toast
import com.resurrection.base.utils.getFunctionIndexByName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(view: View, savedInstanceState: Bundle?) {
        viewModel.getPhotos()

        viewModel.photos.observeData(
            success = {
                binding.textView.text = it.toString()
            }
        )

/*
        binding.textView.text = viewModel.photos.getFunctionIndexByName("postValue").toString()
*/

    }
}
