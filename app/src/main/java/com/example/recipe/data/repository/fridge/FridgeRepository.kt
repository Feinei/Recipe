package com.example.recipe.data.repository.fridge

import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.FridgeFood

interface FridgeRepository {

    suspend fun insert(food: FridgeFood)
    suspend fun insert(food: Food, grams: Int)
    suspend fun getAllFood(): List<FridgeFood>
    suspend fun deleteFoodById(id: Long)
}
