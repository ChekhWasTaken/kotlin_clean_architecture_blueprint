package com.example.db

import android.content.Context
import androidx.room.Room

class AppDatabaseFactory {
    companion object {
        fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "appDb").build()
    }
}