package com.example.framework

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    companion object {
        private fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

        private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        fun create(
            baseUrl: String,
            gsonConverterFactory: GsonConverterFactory = gsonConverterFactory(),
            client: OkHttpClient = okHttpClient()
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build()
        }
    }
}