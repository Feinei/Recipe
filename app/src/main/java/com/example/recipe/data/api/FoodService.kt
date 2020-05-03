package com.example.recipe.data.api

import com.example.recipe.data.db.entities.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {

    companion object {
        const val ENDPOINT = "https://api.edamam.com/api/food-database/"
    }

    @GET("parser")
    suspend fun foodByName(@Query("ingr") name: String): Response<ResultResponse<Food>>
}