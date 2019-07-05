package com.example.jsonplaceholderclient.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
internal class AppModule(private val context: Context) {
    @Provides
    fun context() = context
}