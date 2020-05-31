package com.example.recipe.di.module

import android.app.Application
import androidx.room.Room
import com.example.recipe.data.db.AppDatabase
import com.example.recipe.data.entities.mapper.FoodMapper
import com.example.recipe.data.entities.mapper.RecipeMapper
import com.example.recipe.di.annotations.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @AppScope
    @Provides
    fun provideDb(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "recipe.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @AppScope
    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()

    @AppScope
    @Provides
    fun provideFridgeDao(db: AppDatabase) = db.fridgeDao()

    @AppScope
    @Provides
    fun provideEatenDao(db: AppDatabase) = db.eatenDao()

    @AppScope
    @Provides
    fun provideRecipeDao(db: AppDatabase) = db.recipeDao()

    @AppScope
    @Provides
    fun provideFoodMapper(): FoodMapper = FoodMapper()

    @AppScope
    @Provides
    fun provideRecipeMapper(): RecipeMapper = RecipeMapper()
}
