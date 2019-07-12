package com.example.jsonplaceholderclient.di

import com.example.api.ApiServiceFactory
import com.example.api.ApiServiceWrapper
import com.example.framework.RetrofitFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
internal object ApiModule {
    @Provides
    @JvmStatic
    @Singleton
    fun retrofit(@Named("baseUrl") baseUrl: String): Retrofit = RetrofitFactory.create(baseUrl)

    @Provides
    @JvmStatic
    @Singleton
    fun apiService(retrofit: Retrofit): ApiServiceWrapper = ApiServiceFactory.create(retrofit)
}