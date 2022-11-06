package com.resurrection.appbase.ui.photo

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import com.resurrection.base.utils.getFunctionIndexByName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


@AndroidEntryPoint
class PhotosFragment : CoreFragment(R.layout.fragment_photos) {

    val binding by dataBinding<FragmentPhotosBinding>()
    val viewModel by viewModel(PhotosViewModel::class.java)

    override fun init(view: View, savedInstanceState: Bundle?) {
/*        viewModel.getPhotos()*/

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
