package com.example.recipe.domain.fridge

import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.data.repository.fridge.FridgeRepository
import com.example.recipe.di.annotations.AppScope
import javax.inject.Inject

@AppScope
class FridgeInteractorImpl @Inject constructor(
    private val repository: FridgeRepository
) : FridgeInteractor {

    override suspend fun getAllFood(): List<FridgeFood> {
        return repository.getAllFood()
    }

    override suspend fun deleteFoodById(id: Long) {
        repository.deleteFoodById(id)
    }
}
