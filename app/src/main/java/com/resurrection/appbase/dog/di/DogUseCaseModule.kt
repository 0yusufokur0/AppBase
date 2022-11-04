package com.resurrection.appbase.dog.di

import com.resurrection.appbase.dog.data.repository.DogRepository
import com.resurrection.appbase.dog.usecase.GetDogUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*@Module
@InstallIn(SingletonComponent::class)
abstract class DogUseCaseModule {

    @Binds
    @Singleton
    abstract fun provideDogUseCase(getDogUseCase: GetDogUseCase): GetDogUseCase

}*/
