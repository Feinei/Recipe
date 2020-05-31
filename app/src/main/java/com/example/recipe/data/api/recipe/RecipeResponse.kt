package com.example.recipe.data.api.recipe

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("hits")
    val hints: List<Recipe>
)

data class Recipe(
    @SerializedName("recipe")
    val recipeDetail: RecipeDetail
)

data class RecipeDetail(
    @SerializedName("label")
    val label: String,
    @SerializedName("url")
    val recipeSource: String,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("totalTime")
    val timeMinutes: Int,
    @SerializedName("totalNutrients")
    val nutrients: Nutrients,
    @SerializedName("image")
    val imageSource: String?
)

data class Ingredient(
    @SerializedName("text")
    val name: String,
    @SerializedName("weight")
    val weightGrams: Double
)

data class Nutrients(
    @SerializedName("ENERC_KCAL")
    val cals: Nutrient,
    @SerializedName("PROCNT")
    val protein: Nutrient,
    @SerializedName("FAT")
    val fat: Nutrient,
    @SerializedName("CHOCDF")
    val carbs: Nutrient
)

data class Nutrient(
    @SerializedName("quantity")
    val quantity: Float
)
