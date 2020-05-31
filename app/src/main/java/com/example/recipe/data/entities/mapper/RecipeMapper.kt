package com.example.recipe.data.entities.mapper

import com.example.recipe.data.entities.Ingredient
import com.example.recipe.data.entities.Recipe

class RecipeMapper {

    fun fromResponseToModel(list: List<com.example.recipe.data.api.recipe.Recipe>): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        for (recipe in list) {
            val info = recipe.recipeDetail
            val listIngr = mutableListOf<Ingredient>()
            for (ingr in info.ingredients)
                listIngr.add(Ingredient(ingr.name, ingr.weightGrams.toInt()))
            recipes.add(
                Recipe(
                    0,
                    info.label,
                    info.recipeSource,
                    info.imageSource,
                    info.nutrients.cals.quantity.toDouble(),
                    info.nutrients.protein.quantity.toDouble(),
                    info.nutrients.fat.quantity.toDouble(),
                    info.nutrients.carbs.quantity.toDouble(),
                    info.timeMinutes,
                    listIngr
                )
            )
        }
        return recipes
    }
}
