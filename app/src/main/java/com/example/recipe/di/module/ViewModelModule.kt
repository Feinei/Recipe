package com.example.recipe.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.utils.ViewModelFactory
import com.example.recipe.ui.food.FoodListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
