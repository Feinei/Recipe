package com.example.recipe.data.api.recipe

import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun recipeByName(@Query("q") name: String): RecipeResponse
}
