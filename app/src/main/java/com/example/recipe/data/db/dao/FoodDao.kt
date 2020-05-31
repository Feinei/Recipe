package com.example.recipe.data.db.dao

import androidx.room.*
import com.example.recipe.data.entities.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM food WHERE name = :name")
    fun getFoodByName(name: String): List<Food>
}
