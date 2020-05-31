package com.example.recipe

import android.app.Application
import com.example.recipe.di.utils.AppInjector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
