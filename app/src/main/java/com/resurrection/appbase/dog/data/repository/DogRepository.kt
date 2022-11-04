package com.resurrection.appbase.dog.data.repository

import com.resurrection.appbase.dog.data.model.DogModel
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDog(): Flow<Resource<DogModel>>
}