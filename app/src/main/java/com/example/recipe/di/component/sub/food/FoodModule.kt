package com.example.recipe.di.component.sub.food

import androidx.lifecycle.ViewModel
import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.module.ViewModelModule
import com.example.recipe.ui.food.FoodListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface FoodModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(FoodListViewModel::class)
    fun bindFoodViewModel(
        foodListViewModel: FoodListViewModel
    ): ViewModel
}
