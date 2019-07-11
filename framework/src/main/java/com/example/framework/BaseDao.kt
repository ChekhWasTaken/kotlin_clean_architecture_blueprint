package com.example.framework

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {
    @Insert
    suspend fun insert(vararg entities: T)

    @Update
    suspend fun update(vararg entities: T)

    @Delete
    suspend fun delete(vararg entities: T)
}