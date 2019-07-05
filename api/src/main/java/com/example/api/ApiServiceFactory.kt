package com.example.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class ApiServiceFactory {
    companion object {
        private fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

        private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        private fun retrofit(
            baseUrl: String,
            gsonConverterFactory: GsonConverterFactory,
            client: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build()
        }

        fun create(): ApiServiceWrapper = with(ApiServiceWrapper()) {
            apiService = retrofit(BASE_URL, gsonConverterFactory(), okHttpClient()).create(ApiService::class.java)
            this
        }
    }
}