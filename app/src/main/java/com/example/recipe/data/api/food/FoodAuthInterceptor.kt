package com.example.recipe.data.api.food

import com.example.recipe.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodAuthInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val CATEGORY = "category"
        private const val CATEGORY_1 = "generic-foods"
        private const val CATEGORY_2 = "generic-meals"
        private const val APP_ID = "app_id"
        private const val APP_KEY = "app_key"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter(CATEGORY, CATEGORY_1)
            .addQueryParameter(CATEGORY, CATEGORY_2)
            .addQueryParameter(APP_ID, BuildConfig.FOOD_APP_ID)
            .addQueryParameter(APP_KEY, BuildConfig.FOOD_API_KEY)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
