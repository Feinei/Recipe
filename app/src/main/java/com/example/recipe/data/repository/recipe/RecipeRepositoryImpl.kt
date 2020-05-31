package com.example.recipe.data.repository.recipe

import com.example.recipe.data.api.recipe.RecipeService
import com.example.recipe.data.entities.Recipe
import com.example.recipe.data.entities.mapper.RecipeMapper
import com.example.recipe.data.repository.recipe.RecipeRepository
import com.example.recipe.di.annotations.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class RecipeRepositoryImpl @Inject constructor(
    private var recipeService: RecipeService,
    private var mapper: RecipeMapper
) : RecipeRepository {

    override suspend fun searchRecipe(name: String): List<Recipe> = withContext(Dispatchers.IO) {
        val response = recipeService.recipeByName(name)
        val recipe = mapper.fromResponseToModel(response.hints)
        recipe
    }
}
