package com.example.recipe.ui.cals

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.data.entities.Nutrients
import com.example.recipe.domain.cals.CaloriesInteractor
import com.example.recipe.ui.MainActivity
import com.example.recipe.utils.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class CaloriesViewModel @Inject constructor(private val interactor: CaloriesInteractor) :
    ViewModel() {

    private lateinit var viewModelJob: Job

    private var mutableFood = MutableLiveData<List<EatenFood>>()
    val food: LiveData<List<EatenFood>>
        get() = mutableFood

    private var mutableProgress = MutableLiveData<Status>()
    val progress: LiveData<Status>
        get() = mutableProgress

    private var mutableNutrients = MutableLiveData<Nutrients>()
    val nutrients: LiveData<Nutrients>
        get() = mutableNutrients

    init {
        loadFood()
    }

    fun loadFood() {
        viewModelJob = viewModelScope.launch {
            try {
                mutableProgress.postValue(Status.RUNNING)
                val todayFood = interactor.getTodayEatenFood()
                val currentNutrients = interactor.getCurrentNutrients()

                mutableFood.postValue(todayFood)
                mutableNutrients.postValue(currentNutrients)
                mutableProgress.postValue(Status.SUCCESS)

            } catch (e: HttpException) {
                mutableProgress.postValue(Status.FAILED)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun deleteFoodById(id: Long) {
        viewModelJob = viewModelScope.launch {
            interactor.deleteFoodById(id)

            val todayFood = interactor.getTodayEatenFood()
            val currentNutrients = interactor.getCurrentNutrients()

            mutableFood.postValue(todayFood)
            mutableNutrients.postValue(currentNutrients)
        }
    }
}
