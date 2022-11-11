package com.resurrection.appbase.dog.di

import com.resurrection.appbase.dog.data.remote.DogApiService
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
class DogModule {

    companion object {
        private const val BASE_URL = "https://dog.ceo/"
    }

    @Provides
    @Singleton
    fun provideDogApiService(): DogApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApiService::class.java)
}