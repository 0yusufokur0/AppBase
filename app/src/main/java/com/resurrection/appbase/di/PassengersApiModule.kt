package com.resurrection.appbase.di

import com.resurrection.appbase.data.remote.PassengersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PassengersApiModule {

    private val BASE_URL = "https://api.instantwebtools.net/v1/"

    @Provides
    @Singleton
    fun providePassengersApiService(): PassengersApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().also { client ->
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PassengersApiService::class.java)


}