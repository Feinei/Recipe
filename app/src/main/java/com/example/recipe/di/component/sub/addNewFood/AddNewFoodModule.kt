package com.example.recipe.di.component.sub.addNewFood

import androidx.lifecycle.ViewModel
import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.module.ViewModelModule
import com.example.recipe.ui.general.AddNewFoodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface AddNewFoodModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(AddNewFoodViewModel::class)
    fun bindAddNewFoodViewModel(
        addNewFoodViewModel: AddNewFoodViewModel
    ): ViewModel
}
