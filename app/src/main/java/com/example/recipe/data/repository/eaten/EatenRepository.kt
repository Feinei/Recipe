package com.example.recipe.data.repository.eaten

import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.FridgeFood

interface EatenRepository {

    suspend fun insert(food: EatenFood)
    suspend fun insert(food: Food, grams: Int, date: Long)
    suspend fun getFoodByDate(from: Long, to: Long): List<EatenFood>
    suspend fun deleteFoodById(id: Long)
}
