package com.example.recipe.di.component.sub.addNewFood

import com.example.recipe.di.annotations.ScreenScope
import com.example.recipe.ui.general.AddNewFoodFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [AddNewFoodModule::class])
interface AddNewFoodComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddNewFoodComponent
    }

    fun inject(fragment: AddNewFoodFragment)
}
