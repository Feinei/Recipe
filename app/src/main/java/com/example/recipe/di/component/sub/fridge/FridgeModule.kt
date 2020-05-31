package com.example.recipe.di.component.sub.fridge

import androidx.lifecycle.ViewModel
import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.module.ViewModelModule
import com.example.recipe.ui.fridge.FridgeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface FridgeModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(FridgeViewModel::class)
    fun bindFridgeViewModel(
        fridgeViewModel: FridgeViewModel
    ): ViewModel
}
