package com.example.recipe.di.component.sub.recipe

import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.ui.recipe.RecipeFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [RecipeModule::class])
interface RecipeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RecipeComponent
    }

    fun inject(fragment: RecipeFragment)
}
