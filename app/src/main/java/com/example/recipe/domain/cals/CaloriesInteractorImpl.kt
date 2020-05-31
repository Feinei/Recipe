package com.example.recipe.domain.cals

import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.Nutrients
import com.example.recipe.data.repository.eaten.EatenRepository
import com.example.recipe.di.annotations.AppScope
import java.util.*
import javax.inject.Inject

@AppScope
class CaloriesInteractorImpl @Inject constructor(
    private val eatenRepository: EatenRepository
) : CaloriesInteractor {

    override suspend fun getTodayEatenFood(): List<EatenFood> {
        return eatenRepository.getFoodByDate(
            getTimeInMillis(0, 0, 1),
            getTimeInMillis(23, 59, 59)
        )
    }

    override suspend fun getCurrentNutrients(): Nutrients {
        val todayFood = eatenRepository.getFoodByDate(
            getTimeInMillis(0, 0, 1),
            getTimeInMillis(23, 59, 59)
        )
        val currentNutrients = Nutrients(0.0, 0.0, 0.0, 0.0)
        for (food in todayFood) {
            currentNutrients.cals += food.cals * food.grams * 0.01
            currentNutrients.carbs += food.carbs * food.grams * 0.01
            currentNutrients.fat += food.fat * food.grams * 0.01
            currentNutrients.protein += food.protein * food.grams * 0.01
        }
        return currentNutrients
    }

    override suspend fun deleteFoodById(id: Long) {
        eatenRepository.deleteFoodById(id)
    }

    private fun getTimeInMillis(hour: Int, minute: Int, second: Int): Long {
        val currentDate = Calendar.getInstance()
        val time = Calendar.getInstance()
        time.set(
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH),
            hour,
            minute,
            second
        )
        return time.timeInMillis
    }
}
