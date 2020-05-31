package com.example.recipe.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.recipe.data.entities.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * from recipe WHERE name = :name")
    fun getRecipeByName(name: String): List<Recipe>
}
