package com.example.recipe.di.component.sub.cals

import androidx.lifecycle.ViewModel
import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.module.ViewModelModule
import com.example.recipe.ui.cals.CaloriesViewModel
import com.example.recipe.ui.fridge.FridgeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface CaloriesModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(CaloriesViewModel::class)
    fun bindCaloriesViewModel(
        caloriesViewModel: CaloriesViewModel
    ): ViewModel
}
