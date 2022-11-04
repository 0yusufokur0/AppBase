package com.resurrection.appbase.dog.data.remote

import com.resurrection.appbase.dog.data.model.DogModel
import retrofit2.Response

interface DogApiService {
    suspend fun getDog():Response<DogModel>
}