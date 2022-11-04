package com.resurrection.appbase.dog.ui

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentDogBinding
import com.resurrection.base.core.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogFragment:BaseFragment<FragmentDogBinding,DogViewModel>(
    R.layout.fragment_dog,DogViewModel::class.java
) {
    override fun init(view: View, savedInstanceState: Bundle?) {
        viewModel.getDog()

        viewModel.dog.observeData(
            success = {
                binding.textView.text = "Succeesss"
            }, error = {
                binding.textView.text = "Error"
            }
        )
    }
}

