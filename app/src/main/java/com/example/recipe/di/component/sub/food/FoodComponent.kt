package com.example.recipe.di.component.sub.food

import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.ui.food.FoodListFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [FoodModule::class])
interface FoodComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FoodComponent
    }

    fun inject(fragment: FoodListFragment)
}
