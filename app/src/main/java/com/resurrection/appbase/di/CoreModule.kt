package com.resurrection.appbase.di

import android.content.Context
import com.resurrection.base.components.appstate.AppState
import com.resurrection.base.components.dataholder.DataHolder
import com.resurrection.base.components.dataholder.DataHolderImpl
import com.resurrection.base.components.datastore.DataStore
import com.resurrection.base.components.datastore.DataStoreImpl
import com.resurrection.base.components.logger.LoggerManager
import com.resurrection.base.components.logger.LoggerManagerImpl
import com.resurrection.base.components.network.NetworkManager
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.components.security.BiometricManager
import com.resurrection.base.components.security.CryptographyManager
import com.resurrection.base.components.security.SecurityManager
import com.resurrection.base.components.sharedpreferences.SharedPreferences
import com.resurrection.base.components.sharedpreferences.SharedPreferencesImpl
import com.resurrection.base.components.typeconverter.TypeConverter
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
        dataHolder: DataHolder,
        networkManager: NetworkManager,
        securityManager: SecurityManager
    ) = AppState(context, dataHolder, networkManager, securityManager)

    @Provides
    @Singleton
    fun provideDataHolder(): DataHolder = DataHolderImpl()

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context,
        typeConverter: TypeConverter
    ): SharedPreferences = SharedPreferencesImpl(
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    )

    @Singleton
    @Provides
    fun provideCryptographyManager() = CryptographyManager()

    @Singleton
    @Provides
    fun provideLogger(@ApplicationContext context: Context): LoggerManager =
        LoggerManagerImpl(context)

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
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStore = DataStoreImpl(context)

    @Singleton
    @Provides
    fun provideOkHttpClientManager(typeConverter: TypeConverter) =
        OkHttpClientManager(typeConverter)
}