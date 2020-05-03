package com.example.recipe

import android.app.Activity
import android.app.Application
import com.example.recipe.di.component.DaggerAppComponent
import com.example.recipe.di.component.FoodComponent
import com.example.recipe.di.module.FoodModule
import com.example.recipe.di.utils.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    companion object {
        lateinit var foodComponent: FoodComponent
    }

    // +model mapping
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        var appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)

        foodComponent = appComponent.addFoodComponent(FoodModule())
    }

    override fun activityInjector() = dispatchingAndroidInjector
}