package com.example.recipe.di.module

import com.example.recipe.data.repository.eaten.EatenRepository
import com.example.recipe.data.repository.eaten.EatenRepositoryImpl
import com.example.recipe.data.repository.food.FoodRepository
import com.example.recipe.data.repository.food.FoodRepositoryImpl
import com.example.recipe.data.repository.fridge.FridgeRepository
import com.example.recipe.data.repository.fridge.FridgeRepositoryImpl
import com.example.recipe.data.repository.recipe.RecipeRepository
import com.example.recipe.data.repository.recipe.RecipeRepositoryImpl
import com.example.recipe.di.annotations.AppScope
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @AppScope
    fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository

    @Binds
    @AppScope
    fun bindFridgeRepository(fridgeRepositoryImpl: FridgeRepositoryImpl): FridgeRepository

    @Binds
    @AppScope
    fun bindEatenRepository(eatenRepositoryImpl: EatenRepositoryImpl): EatenRepository

    @Binds
    @AppScope
    fun bindRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository
}
