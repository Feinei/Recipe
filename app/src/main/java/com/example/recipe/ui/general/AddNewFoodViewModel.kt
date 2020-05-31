package com.example.recipe.ui.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.entities.Food
import com.example.recipe.domain.addNewFood.AddNewFoodInteractor
import com.example.recipe.utils.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNewFoodViewModel @Inject constructor(
    private val interactor: AddNewFoodInteractor
) : ViewModel() {

    private lateinit var viewModelJob: Job

    fun addFoodToDestination(
        requestAdress: String?,
        name: String,
        cals: String,
        carbo: String,
        protein: String,
        fat: String,
        grams: String
    ) {
        viewModelJob = viewModelScope.launch {
            val insertData = Food(
                0,
                name,
                null,
                cals.toDouble(),
                protein.toDouble(),
                fat.toDouble(),
                carbo.toDouble(),
                0.0
            )
            if (requestAdress == Constants.FROM_FRIDGE)
                interactor.insertFoodInFridge(insertData, grams.toInt())
            else
                interactor.insertFoodInEaten(insertData, grams.toInt())
        }
    }
}
