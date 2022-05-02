package com.resurrection.base.di

import android.content.Context
import android.os.Bundle
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.data.DataHolderManager
import com.resurrection.base.components.data.DataStoreManager
import com.resurrection.base.components.data.SharedPreferencesManager
import com.resurrection.base.components.data.TypeConverter
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.components.security.BiometricManager
import com.resurrection.base.components.security.CryptographyManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.widget.AppLoadingIndicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

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
    fun provideCryptographyManager() = CryptographyManager()

    @Singleton
    @Provides
    fun provideLogger(@ApplicationContext context: Context) = LoggerManager(context)

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
    fun provideBiometricManager() = BiometricManager()

    @Singleton
    @Provides
    fun provideTypeConverter() = TypeConverter()

    @Singleton
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context) = DataStoreManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClientManager(typeConverter: TypeConverter) = OkHttpClientManager(typeConverter)

}