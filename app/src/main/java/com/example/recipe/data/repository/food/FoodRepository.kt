package com.example.recipe.data.repository.food

import com.example.recipe.data.entities.Food

interface FoodRepository {

    suspend fun searchFood(name: String): List<Food>
}
