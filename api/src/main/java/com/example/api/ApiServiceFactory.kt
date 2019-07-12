package com.example.api

import retrofit2.Retrofit

class ApiServiceFactory {
    companion object {
        fun create(retrofit: Retrofit): ApiServiceWrapper = with(ApiServiceWrapper()) {
            apiService = retrofit.create(ApiService::class.java)
            this
        }
    }
}