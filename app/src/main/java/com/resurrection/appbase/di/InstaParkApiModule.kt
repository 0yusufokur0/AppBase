package com.veripark.instapark.di

import com.veripark.instapark.data.remote.InstaParkApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstaParkApiModule {
    private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

/*
    https://jsonplaceholder.typicode.com/users
    https://jsonplaceholder.typicode.com/posts
*/
    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createApi(retrofit: Retrofit): InstaParkApiService =
        retrofit.create(InstaParkApiService::class.java)
}
