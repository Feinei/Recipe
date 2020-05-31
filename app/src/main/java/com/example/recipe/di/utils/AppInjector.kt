package com.example.recipe.di.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.recipe.App
import com.example.recipe.di.component.AppComponent
import com.example.recipe.di.component.DaggerAppComponent
import com.example.recipe.di.component.sub.addNewFood.AddNewFoodComponent
import com.example.recipe.di.component.sub.cals.CaloriesComponent
import com.example.recipe.di.component.sub.food.FoodComponent
import com.example.recipe.di.component.sub.fridge.FridgeComponent
import com.example.recipe.di.component.sub.recipe.RecipeComponent
import com.example.recipe.ui.MainActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

object AppInjector {

    lateinit var appComponent: AppComponent
    private var foodComponent: FoodComponent? = null
    private var fridgeComponent: FridgeComponent? = null
    private var addNewFoodComponent: AddNewFoodComponent? = null
    private var caloriesComponent: CaloriesComponent? = null
    private var recipeComponent: RecipeComponent? = null

    fun init(application: App) {
        DaggerAppComponent.builder()
            .application(application)
            .build().also { appComponent = it }
            .inject(application)
    }

    fun injectMainActivity(mainActivity: MainActivity) {
        appComponent.inject(mainActivity)
    }

    fun plusFoodComponent(): FoodComponent =
        foodComponent ?: appComponent
            .foodComponentFactory()
            .create()
            .also {
                foodComponent = it
            }

    fun plusFridgeComponent(): FridgeComponent =
        fridgeComponent ?: appComponent
            .fridgeComponentFactory()
            .create()
            .also {
                fridgeComponent = it
            }

    fun plusAddNewFoodComponent(): AddNewFoodComponent =
        addNewFoodComponent ?: appComponent
            .addNewFoodComponentFactory()
            .create()
            .also {
                addNewFoodComponent = it
            }

    fun plusCaloriesComponent(): CaloriesComponent =
        caloriesComponent ?: appComponent
            .caloriesComponentFactory()
            .create()
            .also {
                caloriesComponent = it
            }

    fun plusRecipeComponent(): RecipeComponent =
        recipeComponent ?: appComponent
            .recipeComponentFactory()
            .create()
            .also {
                recipeComponent = it
            }

    fun clearFoodComponent() {
        foodComponent = null
    }

    fun clearFridgeComponent() {
        fridgeComponent = null
    }

    fun clearAddNewFoodComponent() {
        addNewFoodComponent = null
    }

    fun clearCaloriesComponent() {
        caloriesComponent = null
    }

    fun clearRecipeComponent() {
        recipeComponent = null
    }
}
