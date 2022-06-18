package com.resurrection.appbase.data.remote

import com.resurrection.appbase.data.model.passenger.PassengersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PassengersApiService {

    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengersResponse

}

