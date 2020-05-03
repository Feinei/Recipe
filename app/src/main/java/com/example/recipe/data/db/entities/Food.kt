package com.example.recipe.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "foodName")
    var foodName: String,
    @ColumnInfo(name = "foodDescription")
    var foodDescription: String?,
    @ColumnInfo(name = "grams")
    var grams: Int?,
    @ColumnInfo(name = "cals")
    var cals: Double,
    @ColumnInfo(name = "protein")
    var protein: Double,
    @ColumnInfo(name = "fat")
    var fat: Double,
    @ColumnInfo(name = "carbs")
    var carbs: Double,
    @ColumnInfo(name = "fiber")
    var fiber: Double
)
