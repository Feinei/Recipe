package com.example.recipe.data.api.food

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("text")
    val text: String,
    @SerializedName("parsed")
    val searchInfo: List<Food>,
    @SerializedName("hints")
    val hints: List<Food>
)

data class Food(
    @SerializedName("food")
    val foodDetail: FoodDetail
)

data class FoodDetail(
    @SerializedName("label")
    val label: String,
    @SerializedName("nutrients")
    val nutrients: Nutrients,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val imageSource: String?
)

data class Nutrients(
    @SerializedName("ENERC_KCAL")
    val cals: Double,
    @SerializedName("PRONCNT")
    val protein: Double,
    @SerializedName("FAT")
    val fat: Double,
    @SerializedName("CHOCDF")
    val carbs: Double,
    @SerializedName("FIBTG")
    val fiber: Double
)
