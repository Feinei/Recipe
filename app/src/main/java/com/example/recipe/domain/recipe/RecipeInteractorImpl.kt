package com.example.recipe.domain.recipe

import com.example.recipe.data.entities.Recipe
import com.example.recipe.data.repository.recipe.RecipeRepository
import com.example.recipe.di.annotations.AppScope
import com.example.recipe.domain.recipe.RecipeInteractor
import javax.inject.Inject

@AppScope
class RecipeInteractorImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    RecipeInteractor {

    override suspend fun getRecipeByName(name: String): List<Recipe> {
        return recipeRepository.searchRecipe(name)
    }

    override suspend fun getDetailRecipe(name: String): Recipe {
        return recipeRepository.searchRecipe(name)[0]
    }
}
