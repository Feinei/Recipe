package com.example.recipe.di.component.sub.recipe

import androidx.lifecycle.ViewModel
import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.annotations.ViewModelKey
import com.example.recipe.di.module.ViewModelModule
import com.example.recipe.ui.recipe.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface RecipeModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(RecipeViewModel::class)
    fun bindRecipeViewModel(
        recipeViewModel: RecipeViewModel
    ): ViewModel
}
