package com.resurrection.base.di

import android.content.Context
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.datastore.DataStoreManagerImpl
import com.resurrection.base.components.typeconverter.TypeConverter
import com.resurrection.base.components.dataholder.DataHolderManager
import com.resurrection.base.components.dataholder.DataHolderManagerImpl
import com.resurrection.base.components.datastore.DataStoreManager
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.logger.LoggerManagerImpl
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.components.security.BiometricManager
import com.resurrection.base.components.security.CryptographyManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManager
import com.resurrection.base.components.sharedpreferences.SharedPreferencesManagerImpl
import com.resurrection.base.components.widget.AppLoadingIndicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideAppState(
        @ApplicationContext context: Context,
        dataHolderManager: DataHolderManager,
        networkManager: NetworkManager,
        securityManager: SecurityManager
    ) = AppState(context, dataHolderManager, networkManager, securityManager)

    @Provides
    @Singleton
    fun provideDataHolder(): DataHolderManager = DataHolderManagerImpl()

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context,
        typeConverter: TypeConverter
    ): SharedPreferencesManager = SharedPreferencesManagerImpl(context, typeConverter)

    @Singleton
    @Provides
    fun provideCryptographyManager() = CryptographyManager()

    @Singleton
    @Provides
    fun provideLogger(@ApplicationContext context: Context): LoggerManager = LoggerManagerImpl(context)

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
    fun provideDataStoreManager(@ApplicationContext context: Context):DataStoreManager = DataStoreManagerImpl(context)

    @Singleton
    @Provides
    fun provideOkHttpClientManager(typeConverter: TypeConverter) =
        OkHttpClientManager(typeConverter)

}