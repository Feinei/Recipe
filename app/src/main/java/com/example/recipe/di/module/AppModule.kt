package com.example.recipe.di.module

import android.app.Application
import com.example.recipe.di.annotations.CoroutineScopeIO
import com.example.recipe.di.annotations.FoodAPI
import com.example.recipe.data.api.AuthInterceptor
import com.example.recipe.data.api.FoodRemoteDataSource
import com.example.recipe.data.api.FoodService
import com.example.recipe.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetModule::class])
class AppModule() {

    @Singleton
    @Provides
    fun provideFoodService(
        @FoodAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, FoodService::class.java)

    @Singleton
    @Provides
    fun provideFoodRemoteDataSource(foodService: FoodService) =
        FoodRemoteDataSource(foodService)

    @FoodAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FoodService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, mClass: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(mClass)
    }
}
