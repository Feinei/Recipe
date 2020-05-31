package com.example.recipe.ui.fridge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.domain.fridge.FridgeInteractor
import com.example.recipe.utils.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class FridgeViewModel @Inject constructor(private val interactor: FridgeInteractor) : ViewModel() {

    private lateinit var viewModelJob: Job

    private var mutableFood = MutableLiveData<List<FridgeFood>>()
    val food: LiveData<List<FridgeFood>>
        get() = mutableFood

    private var mutableProgress = MutableLiveData<Status>()
    val progress: LiveData<Status>
        get() = mutableProgress

    init {
        loadFood()
    }

    fun loadFood() {
        viewModelJob = viewModelScope.launch {
            try {
                mutableProgress.postValue(Status.RUNNING)
                val response = interactor.getAllFood()

                mutableFood.postValue(response)
                mutableProgress.postValue(Status.SUCCESS)

            } catch (e: HttpException) {
                mutableProgress.postValue(Status.FAILED)
            }
        }
    }

    fun deleteFoodById(id: Long) {
        viewModelJob = viewModelScope.launch {
            interactor.deleteFoodById(id)

            val response = interactor.getAllFood()
            mutableFood.postValue(response)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
