package com.resurrection.appbase.dog.data.repository

import com.resurrection.appbase.dog.data.model.DogModel
import com.resurrection.appbase.dog.data.remote.DogApiService
import com.resurrection.base.components.network.resourcefulFlowOfRemote
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(private val dogApiService: DogApiService) : DogRepository {
    override suspend fun getDog(): Flow<Resource<DogModel>> {
        return resourcefulFlowOfRemote(dogApiService::getDog)
    }
}