package com.example.jsonplaceholderclient.di

import com.example.api.ApiServiceFactory
import com.example.api.ApiServiceWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ApiModule {
    @Provides
    @Singleton
    fun apiService(): ApiServiceWrapper = ApiServiceFactory.create()
}