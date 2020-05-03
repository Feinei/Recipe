package com.example.recipe.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipe.data.db.entities.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM food WHERE foodName = :name")
    fun getFoodByName(name: String): LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(food: List<Food>)
}