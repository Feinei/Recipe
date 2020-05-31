package com.example.recipe.domain.addNewFood

import com.example.recipe.data.entities.Food

interface AddNewFoodInteractor {

    suspend fun insertFoodInFridge(food: Food, grams: Int)
    suspend fun insertFoodInEaten(food: Food, grams: Int)
}
