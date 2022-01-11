package com.resurrection.base.di

import android.content.Context
import android.os.Bundle
import com.resurrection.base.AppSession
import com.resurrection.base.data.DataHolderManager
import com.resurrection.base.data.SharedPreferencesManager
import com.resurrection.base.general.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataHolder(@ApplicationContext context: Context) = DataHolderManager(Bundle())

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context) = SharedPreferencesManager(context)

    @Singleton
    @Provides
    fun provideLogger() = Logger()

    @Singleton
    @Provides
    fun provideAppSession(dataHolder: DataHolderManager, sharedPref: SharedPreferencesManager, logger: Logger) =
        AppSession(dataHolder,sharedPref,logger)

}