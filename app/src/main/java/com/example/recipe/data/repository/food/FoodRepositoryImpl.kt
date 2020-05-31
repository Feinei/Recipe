package com.example.recipe.data.repository.food

import com.example.recipe.data.api.food.FoodService
import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.mapper.FoodMapper
import com.example.recipe.data.repository.food.FoodRepository
import com.example.recipe.di.annotations.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class FoodRepositoryImpl @Inject constructor(
    private var foodService: FoodService,
    private var mapper: FoodMapper
) : FoodRepository {

    override suspend fun searchFood(name: String): List<Food> = withContext(Dispatchers.IO) {
        val response = foodService.foodByName(name)
        val food = mapper.fromResponseToModel(response.hints)
        food
    }
}
