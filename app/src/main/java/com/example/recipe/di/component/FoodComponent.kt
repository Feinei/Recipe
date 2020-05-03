package com.example.recipe.di.component

import androidx.fragment.app.Fragment
import com.example.recipe.di.annotations.FoodScope
import com.example.recipe.di.module.FoodModule
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [FoodModule::class])
@FoodScope
interface FoodComponent {

    fun inject(fragment : Fragment)
}