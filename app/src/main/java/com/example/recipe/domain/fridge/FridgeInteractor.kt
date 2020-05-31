package com.example.recipe.domain.fridge

import com.example.recipe.data.entities.FridgeFood

interface FridgeInteractor {

    suspend fun getAllFood(): List<FridgeFood>
    suspend fun deleteFoodById(id: Long)
}
