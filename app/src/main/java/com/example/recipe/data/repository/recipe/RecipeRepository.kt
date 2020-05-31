package com.example.recipe.data.repository.recipe

import com.example.recipe.data.entities.Recipe

interface RecipeRepository {

    suspend fun searchRecipe(name: String): List<Recipe>
}
