package com.resurrection.appbase.data.repository.samples

import com.resurrection.appbase.data.dummy.samplesDummyData
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SamplesRepositoryImpl @Inject constructor(
    private val okHttpClientManager: OkHttpClientManager
) : SamplesRepository {

    override suspend fun getSampleList() = flow { emit(Resource.Success(samplesDummyData)) }
}