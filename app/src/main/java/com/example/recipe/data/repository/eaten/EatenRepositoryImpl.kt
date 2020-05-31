package com.example.recipe.data.repository.eaten

import com.example.recipe.data.db.dao.EatenDao
import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.Food
import com.example.recipe.data.entities.mapper.FoodMapper
import com.example.recipe.data.repository.eaten.EatenRepository
import com.example.recipe.di.annotations.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class EatenRepositoryImpl @Inject constructor(
    private var eatenDao: EatenDao,
    private var foodMapper: FoodMapper
) : EatenRepository {

    override suspend fun insert(food: EatenFood) = withContext(Dispatchers.IO) {
        eatenDao.insert(food)
    }

    override suspend fun insert(food: Food, grams: Int, date: Long) = withContext(Dispatchers.IO) {
        eatenDao.insert(foodMapper.fromFoodToEatenFood(food, grams, date))
    }

    override suspend fun getFoodByDate(from: Long, to: Long): List<EatenFood> =
        withContext(Dispatchers.IO) {
            eatenDao.getEatenFoodByDate(from, to)
        }

    override suspend fun deleteFoodById(id: Long) = withContext(Dispatchers.IO) {
        eatenDao.deleteFood(id)
    }
}
