package com.example.recipe.di.component.sub.cals

import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.di.component.sub.fridge.FridgeModule
import com.example.recipe.ui.cals.CaloriesFragment
import com.example.recipe.ui.fridge.FridgeFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [CaloriesModule::class])
interface CaloriesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CaloriesComponent
    }

    fun inject(fragment: CaloriesFragment)
}
