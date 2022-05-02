package com.resurrection.appbase.di

import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.appbase.data.repository.InstaParkRepository
import com.resurrection.base.component.NetworkManager
import com.resurrection.base.component.OkHttpClientManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(instaParkApiService: InstaParkApiService,okHttpClientManager: OkHttpClientManager) =
        InstaParkRepository(instaParkApiService,okHttpClientManager)

}