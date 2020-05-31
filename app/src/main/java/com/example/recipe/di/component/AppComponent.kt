package com.example.recipe.di.component

import android.app.Application
import android.content.Context
import com.example.recipe.App
import com.example.recipe.di.annotations.AppScope
import com.example.recipe.di.component.sub.addNewFood.AddNewFoodComponent
import com.example.recipe.di.component.sub.cals.CaloriesComponent
import com.example.recipe.di.component.sub.food.FoodComponent
import com.example.recipe.di.component.sub.fridge.FridgeComponent
import com.example.recipe.di.component.sub.recipe.RecipeComponent
import com.example.recipe.di.module.*
import com.example.recipe.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@AppScope
@Component(
    modules = [
        NetModule::class,
        DatabaseModule::class,
        InteractorModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun foodComponentFactory(): FoodComponent.Factory
    fun fridgeComponentFactory(): FridgeComponent.Factory
    fun addNewFoodComponentFactory(): AddNewFoodComponent.Factory
    fun caloriesComponentFactory(): CaloriesComponent.Factory
    fun recipeComponentFactory(): RecipeComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(mainActivity: MainActivity)
}
