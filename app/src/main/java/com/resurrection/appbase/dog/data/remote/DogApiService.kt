package com.resurrection.appbase.dog.data.remote

import com.resurrection.appbase.dog.data.model.DogModel
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET("api/breeds/image/random")
    suspend fun getDog():Response<DogModel>
}