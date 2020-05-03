package com.example.recipe.data.api

import com.example.recipe.data.api.BaseDataSource
import com.example.recipe.data.api.FoodService
import javax.inject.Inject

class FoodRemoteDataSource @Inject constructor(private val service: FoodService) :
    BaseDataSource() {

    suspend fun fetchData(name: String) = getResult { service.foodByName(name) }

}