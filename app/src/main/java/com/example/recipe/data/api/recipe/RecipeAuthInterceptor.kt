package com.example.recipe.data.api.recipe

import com.example.recipe.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeAuthInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val FROM = "from"
        private const val TO = "to"
        private const val FROM_VALUE = "0"
        private const val TO_VALUE = "100"
        private const val APP_ID = "app_id"
        private const val APP_KEY = "app_key"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter(APP_ID, BuildConfig.RECIPE_APP_ID)
            .addQueryParameter(APP_KEY, BuildConfig.RECIPE_API_KEY)
            .addQueryParameter(FROM, FROM_VALUE)
            .addQueryParameter(TO, TO_VALUE)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
