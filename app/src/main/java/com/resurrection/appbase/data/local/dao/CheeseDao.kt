package com.resurrection.appbase.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.resurrection.appbase.data.model.cheese.Cheese


@Dao
interface CheeseDao {

    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun allCheesesByName(): PagingSource<Int, Cheese>

    @Insert
    fun insert(cheeses: List<Cheese>)

    @Insert
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)
}