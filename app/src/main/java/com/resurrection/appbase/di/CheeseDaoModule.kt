package com.resurrection.appbase.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.resurrection.appbase.data.local.dao.CheeseDao
import com.resurrection.appbase.data.local.dao.CheeseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CheeseDaoModule {

    @Provides
    @Singleton
    fun provideCheeseDatabase(@ApplicationContext context: Context):CheeseDatabase =
        Room.databaseBuilder(context,CheeseDatabase::class.java,"database_name").build()

    @Provides
    @Singleton
    fun provideCheeseDao(cheeseDatabase: CheeseDatabase):CheeseDao = cheeseDatabase.cheeseDao()


}