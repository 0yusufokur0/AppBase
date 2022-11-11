package com.resurrection.appbase.dog.usecase

import com.resurrection.appbase.dog.data.model.DogModel
import com.resurrection.appbase.dog.data.repository.DogRepository
import com.resurrection.base.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDogUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {
    suspend operator fun invoke(): Flow<Resource<DogModel>> = flow { } /* dogRepository.getDog()*/
}