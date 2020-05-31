package com.example.recipe.data.api.food

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {

    @GET("parser")
    suspend fun foodByName(@Query("ingr") name: String): FoodResponse
}
