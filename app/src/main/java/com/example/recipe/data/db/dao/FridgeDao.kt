package com.example.recipe.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe.data.entities.FridgeFood

@Dao
interface FridgeDao {

    @Insert
    fun insert(food: FridgeFood)

    @Query("SELECT * from fridgeFood")
    fun getAllFood(): List<FridgeFood>

    @Delete
    fun deleteFood(food: FridgeFood)

    @Query("DELETE FROM fridgeFood WHERE id = :id")
    fun deleteFood(id: Long)
}
