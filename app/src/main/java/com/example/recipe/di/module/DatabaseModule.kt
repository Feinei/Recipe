package com.example.recipe.di.module

import android.app.Application
import com.example.recipe.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val app: Application) {

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()
}