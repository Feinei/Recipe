package com.example.recipe.di.module

import com.example.recipe.ui.food.FoodListViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeFoodListFragment(): FoodListViewModel
}