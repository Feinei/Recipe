package com.example.recipe.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "imageSource")
    var imageSource: String?,
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
