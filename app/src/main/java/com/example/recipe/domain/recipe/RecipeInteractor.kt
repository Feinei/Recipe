package com.example.recipe.domain.recipe

import com.example.recipe.data.entities.Recipe

interface RecipeInteractor {

    suspend fun getRecipeByName(name: String): List<Recipe>
    suspend fun getDetailRecipe(name: String): Recipe
}
