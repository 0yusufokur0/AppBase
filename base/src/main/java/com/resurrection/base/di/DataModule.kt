package com.resurrection.base.di

import android.content.Context
import android.os.Bundle
import com.resurrection.base.component.*
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
    fun provideAppState() = AppState(isAppForeground = true, isAppLightMode = true,isNetworkAvailable = false,isRooted = false)

    @Provides
    @Singleton
    fun provideDataHolder() = DataHolderManager(Bundle())

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context) = SharedPreferencesManager(context)

    @Singleton
    @Provides
    fun provideLogger() = Logger()

    @Singleton
    @Provides
    fun provideAppLoadingIndicator() = AppLoadingIndicator()

    @Singleton
    @Provides
    fun provideNetworkManger(@ApplicationContext context: Context) = NetworkManager(context)

    @Singleton
    @Provides
    fun provideSecurityManager(@ApplicationContext context: Context) = SecurityManager(context)

    @Singleton
    @Provides
    fun provideBiometricManage() = BiometricManager()

}