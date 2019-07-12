package com.example.jsonplaceholderclient.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun context() = context

    @Provides
    @Named("baseUrl")
    fun baseUrl() = "https://jsonplaceholder.typicode.com/"
}