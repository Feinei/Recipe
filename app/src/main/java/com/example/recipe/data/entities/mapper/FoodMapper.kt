package com.example.recipe.data.entities.mapper

import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.FridgeFood

class FoodMapper {

    fun fromResponseToModel(list: List<com.example.recipe.data.api.food.Food>): List<Food> {
        val food = mutableListOf<Food>()
        for (f in list) {
            val info = f.foodDetail
            food.add(
                Food(
                    0,
                    info.label,
                    info.imageSource,
                    info.nutrients.cals,
                    info.nutrients.protein,
                    info.nutrients.fat,
                    info.nutrients.carbs,
                    info.nutrients.fiber
                )
            )
        }
        return food
    }

    fun fromFoodToFridgeFood(food: Food, grams: Int) = FridgeFood(
        food.id,
        food.name,
        food.imageSource,
        grams,
        food.cals,
        food.protein,
        food.fat,
        food.carbs,
        food.fiber
    )

    fun fromFoodToEatenFood(food: Food, grams: Int, date: Long) = EatenFood(
        food.id,
        food.name,
        food.imageSource,
        grams,
        food.cals,
        food.protein,
        food.fat,
        food.carbs,
        food.fiber,
        date
    )
}
