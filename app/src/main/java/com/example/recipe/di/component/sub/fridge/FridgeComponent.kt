package com.example.recipe.di.component.sub.fridge

import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.ui.fridge.FridgeFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [FridgeModule::class])
interface FridgeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FridgeComponent
    }

    fun inject(fragment: FridgeFragment)
}
