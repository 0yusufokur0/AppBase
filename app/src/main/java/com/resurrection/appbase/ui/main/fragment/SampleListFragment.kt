package com.resurrection.appbase.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentSampleListBinding
import com.resurrection.appbase.ui.main.viewmodel.SampleListViewModel
import com.resurrection.base.core.fragment.BaseFragment


class SampleListFragment : BaseFragment<FragmentSampleListBinding,SampleListViewModel>(
    R.layout.fragment_sample_list,SampleListViewModel::class.java
) {

    override fun init(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}