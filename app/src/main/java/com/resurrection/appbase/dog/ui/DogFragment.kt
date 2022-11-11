package com.resurrection.appbase.dog.ui

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentDogBinding
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogFragment : CoreFragment(R.layout.fragment_dog) {

    val binding by dataBinding<FragmentDogBinding>()
    val viewModel by viewModel<DogViewModel>()

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
