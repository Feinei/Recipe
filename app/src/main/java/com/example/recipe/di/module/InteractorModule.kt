package com.example.recipe.di.module

import com.example.recipe.di.annotations.AppScope
import com.example.recipe.domain.recipe.RecipeInteractor
import com.example.recipe.domain.recipe.RecipeInteractorImpl
import com.example.recipe.domain.addNewFood.AddNewFoodInteractor
import com.example.recipe.domain.addNewFood.AddNewFoodInteractorImpl
import com.example.recipe.domain.cals.CaloriesInteractor
import com.example.recipe.domain.cals.CaloriesInteractorImpl
import com.example.recipe.domain.food.FoodListInteractor
import com.example.recipe.domain.food.FoodListInteractorImpl
import com.example.recipe.domain.fridge.FridgeInteractor
import com.example.recipe.domain.fridge.FridgeInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    @AppScope
    fun provideFoodInteractor(
        foodInteractorImpl: FoodListInteractorImpl
    ): FoodListInteractor

    @Binds
    @AppScope
    fun provideFridgeInteractor(
        fridgeInteractorImpl: FridgeInteractorImpl
    ): FridgeInteractor

    @Binds
    @AppScope
    fun provideAddNewFoodInteractor(
        addNewFoodInteractorImpl: AddNewFoodInteractorImpl
    ): AddNewFoodInteractor

    @Binds
    @AppScope
    fun provideCaloriesInteractor(
        caloriesInteractorImpl: CaloriesInteractorImpl
    ): CaloriesInteractor

    @Binds
    @AppScope
    fun provideRecipeInteractor(
        recipeInteractorImpl: RecipeInteractorImpl
    ): RecipeInteractor
}
