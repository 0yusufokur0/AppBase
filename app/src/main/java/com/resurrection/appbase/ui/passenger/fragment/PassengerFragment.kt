package com.resurrection.appbase.ui.passenger.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPassengerBinding
import com.resurrection.appbase.ui.passenger.viewmodel.PassengerViewModel
import com.resurrection.appbase.ui.passenger.adapter.LoadStateAdapter
import com.resurrection.appbase.ui.passenger.adapter.PassengersAdapter
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.extensions.delegated.viewdatabinding.dataBinding
import com.resurrection.base.extensions.delegated.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PassengerFragment : CoreFragment(R.layout.fragment_passenger) {

    val binding by dataBinding<FragmentPassengerBinding>()
    val viewModel by viewModel<PassengerViewModel>()

    private val passengersAdapter = PassengersAdapter()
    override fun init(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { passengersAdapter.retry() },
                footer = LoadStateAdapter { passengersAdapter.retry() }
            )
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }
}