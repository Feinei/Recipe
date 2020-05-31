package com.example.recipe.di.module

import com.example.recipe.BuildConfig
import com.example.recipe.data.api.food.FoodAuthInterceptor
import com.example.recipe.data.api.food.FoodService
import com.example.recipe.data.api.recipe.RecipeAuthInterceptor
import com.example.recipe.data.api.recipe.RecipeService
import com.example.recipe.di.annotations.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetModule {

    companion object {
        const val TAG_OK_HTTP_FOOD = "ok_http_food"
        const val TAG_RETROFIT_FOOD = "retrofit_food"

        const val TAG_OK_HTTP_RECIPE = "ok_http_recipe"
        const val TAG_RETROFIT_RECIPE = "retrofit_recipe"
    }

    @Provides
    @AppScope
    @Named(TAG_OK_HTTP_FOOD)
    fun provideFoodOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(FoodAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @AppScope
    @Named(TAG_RETROFIT_FOOD)
    fun provideFoodRetrofit(@Named(TAG_OK_HTTP_FOOD) client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.FOOD_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideFoodService(@Named(TAG_RETROFIT_FOOD) retrofit: Retrofit): FoodService {
        return retrofit.create(FoodService::class.java)
    }

    @Provides
    @AppScope
    @Named(TAG_OK_HTTP_RECIPE)
    fun provideRecipeOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(RecipeAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @AppScope
    @Named(TAG_RETROFIT_RECIPE)
    fun provideRecipeRetrofit(@Named(TAG_OK_HTTP_RECIPE) client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.RECIPE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideRecipeService(@Named(TAG_RETROFIT_RECIPE) retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }
}
