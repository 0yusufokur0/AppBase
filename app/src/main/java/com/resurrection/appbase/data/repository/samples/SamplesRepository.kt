package com.resurrection.appbase.data.repository.samples

import com.resurrection.appbase.data.model.sample.SampleModel
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SamplesRepository {
    suspend fun getSampleList():Flow<Resource<List<SampleModel>>>
}