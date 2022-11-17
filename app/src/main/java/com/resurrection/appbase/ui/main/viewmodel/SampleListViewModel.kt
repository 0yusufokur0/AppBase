package com.resurrection.appbase.ui.main.viewmodel

import com.resurrection.appbase.data.model.sample.SampleModel
import com.resurrection.appbase.data.repository.samples.SamplesRepositoryImpl
import com.resurrection.base.core.viewmodel.CoreViewModel
import com.resurrection.base.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleListViewModel @Inject constructor(
    private val samplesRepositoryImpl: SamplesRepositoryImpl
) : CoreViewModel() {

    val samplesLiveData = liveData<Resource<List<SampleModel>>>()

    fun getSampleList() = samplesLiveData.fetchData(request = samplesRepositoryImpl::getSampleList)
}