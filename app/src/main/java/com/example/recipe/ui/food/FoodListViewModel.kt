package com.example.recipe.ui.food

import androidx.lifecycle.ViewModel
import com.example.recipe.data.repository.FoodRepository
import javax.inject.Inject

class FoodListViewModel @Inject constructor(repository: FoodRepository) : ViewModel() {

    private lateinit var foodName: String

    fun fetchFoodName(name: String) {
        foodName = name
    }

    val food = repository.getFood(foodName)
}