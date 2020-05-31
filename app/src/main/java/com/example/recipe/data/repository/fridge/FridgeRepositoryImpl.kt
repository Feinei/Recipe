package com.example.recipe.data.repository.fridge

import com.example.recipe.data.db.dao.FridgeDao
import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.data.entities.mapper.FoodMapper
import com.example.recipe.data.repository.fridge.FridgeRepository
import com.example.recipe.di.annotations.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class FridgeRepositoryImpl @Inject constructor(
    private var fridgeDao: FridgeDao,
    private var foodMapper: FoodMapper
) : FridgeRepository {

    override suspend fun insert(food: FridgeFood) = withContext(Dispatchers.IO) {
        fridgeDao.insert(food)
    }

    override suspend fun insert(food: Food, grams: Int) = withContext(Dispatchers.IO) {
        fridgeDao.insert(foodMapper.fromFoodToFridgeFood(food, grams))
    }

    override suspend fun getAllFood(): List<FridgeFood> = withContext(Dispatchers.IO) {
        val food = fridgeDao.getAllFood()
        food
    }

    override suspend fun deleteFoodById(id: Long) = withContext(Dispatchers.IO) {
        fridgeDao.deleteFood(id)
    }
}
