package com.example.recipe.domain.cals

import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.data.entities.Nutrients

interface CaloriesInteractor {

    suspend fun getTodayEatenFood(): List<EatenFood>
    suspend fun getCurrentNutrients(): Nutrients
    suspend fun deleteFoodById(id: Long)
}
