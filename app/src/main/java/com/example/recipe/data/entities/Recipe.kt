package com.example.recipe.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "recipeSource")
    val recipeSource: String,
    @ColumnInfo(name = "imageSource")
    val imageSource: String?,
    @ColumnInfo(name = "cals")
    val cals: Double,
    @ColumnInfo(name = "protein")
    val protein: Double,
    @ColumnInfo(name = "fat")
    val fat: Double,
    @ColumnInfo(name = "carbs")
    val carbs: Double,
    @ColumnInfo(name = "timeMinutes")
    val timeMinutes: Int,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>?
)
