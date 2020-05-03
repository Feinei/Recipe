package com.example.recipe.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor() : Interceptor {

    companion object {
        private const val CATEGORY = "category"
        private const val CATEGORY_1 = "generic-foods"
        private const val CATEGORY_2 = "generic-meals"
        private const val APP_ID = "5c3952cd"
        private const val API_KEY = "28fa44061944349d91be8cee4a426ade"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter(
                CATEGORY,
                CATEGORY_1
            )
            .addQueryParameter(
                CATEGORY,
                CATEGORY_2
            )
            .addQueryParameter("app_id",
                APP_ID
            )
            .addQueryParameter("app_key",
                API_KEY
            )
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}