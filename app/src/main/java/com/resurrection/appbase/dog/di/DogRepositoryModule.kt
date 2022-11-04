package com.resurrection.appbase.dog.di

import com.resurrection.appbase.di.components.usecase.UseCaseComponent
import com.resurrection.appbase.di.components.usecase.UseCaseScope
import com.resurrection.appbase.dog.data.repository.DogRepository
import com.resurrection.appbase.dog.data.repository.DogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DogRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(dogRepositoryImpl: DogRepositoryImpl):DogRepository
}