package com.example.recipe.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.entities.Food
import com.example.recipe.domain.food.FoodListInteractor
import com.example.recipe.utils.Constants
import com.example.recipe.utils.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class FoodListViewModel @Inject constructor(
    private val interactor: FoodListInteractor
) : ViewModel() {

    private lateinit var viewModelJob: Job

    private var mutableFood = MutableLiveData<List<Food>>()
    val food: LiveData<List<Food>>
        get() = mutableFood

    private var mutableProgress = MutableLiveData<Status>()
    val progress: LiveData<Status>
        get() = mutableProgress

    fun getFood(name: String) {
        viewModelJob = viewModelScope.launch {

            mutableFood.postValue(emptyList())
            mutableProgress.postValue(Status.RUNNING)
            delay(1000)
            try {
                val response = interactor.getFoodByName(name)
                mutableProgress.postValue(Status.SUCCESS)
                if (response.isNotEmpty()) {
                    mutableFood.postValue(response)
                }
            } catch (e: HttpException) {
                mutableProgress.postValue(Status.FAILED)
            }
        }
    }

    fun addFoodToDestination(requestAdress: String?, food: Food, grams: Int) {
        viewModelJob = viewModelScope.launch {
            val insertData = Food(
                0,
                food.name,
                food.imageSource,
                food.cals,
                food.protein,
                food.fat,
                food.carbs,
                food.fiber
            )
            if (requestAdress == Constants.FROM_FRIDGE)
                interactor.insertFoodInFridge(insertData, grams.toInt())
            else
                interactor.insertFoodInEaten(insertData, grams.toInt())
        }
    }

}