package com.example.recipe.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.entities.Recipe
import com.example.recipe.domain.recipe.RecipeInteractor
import com.example.recipe.utils.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor
) : ViewModel() {

    private lateinit var viewModelJob: Job

    private var mutableRecipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = mutableRecipes

    private var mutableProgress = MutableLiveData<Status>()
    val progress: LiveData<Status>
        get() = mutableProgress

    fun getRecipes(name: String) {
        viewModelJob = viewModelScope.launch {

            mutableRecipes.postValue(emptyList())
            mutableProgress.postValue(Status.RUNNING)
            delay(1000)
            try {
                val response = interactor.getRecipeByName(name)
                mutableProgress.postValue(Status.SUCCESS)
                if (response.isNotEmpty()) {
                    mutableRecipes.postValue(response)
                }
            } catch (e: HttpException) {
                mutableProgress.postValue(Status.FAILED)
            }
        }
    }
}
