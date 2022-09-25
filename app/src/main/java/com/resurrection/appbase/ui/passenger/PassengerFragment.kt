package com.resurrection.appbase.ui.passenger

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPassengerBinding
import com.resurrection.appbase.ui.adapter.LoadStateAdapter
import com.resurrection.base.core.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PassengerFragment : BaseFragment<FragmentPassengerBinding, PassengerViewModel>(
    R.layout.fragment_passenger, PassengerViewModel::class.java
) {
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