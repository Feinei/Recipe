package com.example.recipe.domain.food

import com.example.recipe.data.entities.Food

interface FoodListInteractor {

    suspend fun getFoodByName(name: String): List<Food>

    suspend fun insertFoodInFridge(food: Food, grams: Int)
    suspend fun insertFoodInEaten(food: Food, grams: Int)
}
