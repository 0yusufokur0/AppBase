package com.resurrection.appbase.di

import com.veripark.instapark.data.repository.InstaParkRepository
import com.resurrection.appbase.data.repository.InstaParkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(repository: InstaParkRepositoryImpl): InstaParkRepository

}