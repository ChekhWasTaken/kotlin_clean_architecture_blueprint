package com.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db.dao.PostDao
import com.example.db.dao.UserDao
import com.example.db.entity.DbPost
import com.example.db.entity.DbUser

@Database(entities = [DbUser::class, DbPost::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    internal abstract fun getPostDao(): PostDao

    internal abstract fun getUserDao(): UserDao
}