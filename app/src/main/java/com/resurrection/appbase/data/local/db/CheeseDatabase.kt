package com.resurrection.appbase.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.resurrection.appbase.data.local.dao.CheeseDao
import com.resurrection.appbase.data.model.cheese.Cheese

@Database(entities = [Cheese::class], version = 1)
abstract class CheeseDatabase : RoomDatabase() {
    abstract fun cheeseDao(): CheeseDao
}