package com.resurrection.appbase.di

import com.resurrection.appbase.BuildConfig
import com.resurrection.appbase.data.remote.InstaParkApiService
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

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun createApi(retrofit: Retrofit): InstaParkApiService =
        retrofit.create(InstaParkApiService::class.java)
}
