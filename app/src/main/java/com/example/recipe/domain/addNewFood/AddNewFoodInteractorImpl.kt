package com.example.recipe.domain.addNewFood

import com.example.recipe.data.entities.Food
import com.example.recipe.data.repository.eaten.EatenRepository
import com.example.recipe.data.repository.fridge.FridgeRepository
import com.example.recipe.di.annotations.AppScope
import java.util.*
import javax.inject.Inject

@AppScope
class AddNewFoodInteractorImpl @Inject constructor(
    private val fridgeRepository: FridgeRepository,
    private val eatenRepository: EatenRepository
) : AddNewFoodInteractor {

    override suspend fun insertFoodInFridge(food: Food, grams: Int) {
        fridgeRepository.insert(food, grams)
    }

    override suspend fun insertFoodInEaten(food: Food, grams: Int) {
        val currentDate = Calendar.getInstance()
        eatenRepository.insert(food, grams, currentDate.timeInMillis)
    }
}
