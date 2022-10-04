package com.resurrection.appbase.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.resurrection.appbase.R
import com.resurrection.appbase.data.dummy.samplesDummyData
import com.resurrection.appbase.databinding.FragmentSampleListBinding
import com.resurrection.appbase.ui.main.adapter.SamplesRecyclerViewAdapter
import com.resurrection.appbase.ui.main.viewmodel.SampleListViewModel
import com.resurrection.base.core.fragment.BaseFragment
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.extensions.init
import com.resurrection.base.extensions.observeData


class SampleListFragment : BaseFragment<FragmentSampleListBinding,SampleListViewModel>(
    R.layout.fragment_sample_list,SampleListViewModel::class.java
) {
    private val sampleListAdapter = SamplesRecyclerViewAdapter()

    override fun init(view: View, savedInstanceState: Bundle?) {

/*        viewModel.getSampleList()
        viewModel.samplesLiveData.observeData(
            success = {
                loadingIndicator.hide()
                it?.let { samples ->
                    sampleListAdapter.addAll(samples)
                }

            }, loading = {
                loadingIndicator.show()
            }, error = {
                loadingIndicator.hide()
            }
        )*/
        sampleListAdapter.addAll(samplesDummyData)
        binding.samplesRecyclerView.init(sampleListAdapter)
    }
}