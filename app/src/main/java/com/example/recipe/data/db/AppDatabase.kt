package com.example.recipe.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipe.data.db.dao.*
import com.example.recipe.data.entities.*

@Database(
    entities = [Food::class, FridgeFood::class, EatenFood::class, Recipe::class],
    version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun fridgeDao(): FridgeDao
    abstract fun eatenDao(): EatenDao
    abstract fun recipeDao(): RecipeDao
}
