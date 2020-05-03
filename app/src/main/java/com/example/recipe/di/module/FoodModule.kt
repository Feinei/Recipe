package com.example.recipe.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FoodModule {

    @Singleton
    @Provides
    fun provideViewModelFactory() = ViewModelProvider.NewInstanceFactory()
}