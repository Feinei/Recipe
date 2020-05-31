package com.example.recipe.data.db

import androidx.room.TypeConverter
import com.example.recipe.data.entities.Ingredient
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun listToJson(value: List<Ingredient>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Ingredient>::class.java).toList()
}
