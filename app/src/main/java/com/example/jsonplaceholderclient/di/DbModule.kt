package com.example.jsonplaceholderclient.di

import android.content.Context
import com.example.db.AppDatabase
import com.example.db.AppDatabaseFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DbModule {
    @Provides
    @JvmStatic
    @Singleton
    fun appDatabase(context: Context): AppDatabase = AppDatabaseFactory.create(context)
}