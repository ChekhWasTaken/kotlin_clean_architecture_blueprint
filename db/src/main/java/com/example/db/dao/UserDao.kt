package com.example.db.dao

import androidx.room.*
import com.example.db.entity.DbUser
import com.example.db.entity.UserWithPosts

@Dao
internal interface UserDao {
    @Insert
    suspend fun insert(vararg dbUsers: DbUser)

    @Update
    suspend fun update(vararg dbUsers: DbUser)

    @Delete
    suspend fun delete(vararg dbUsers: DbUser)

    @Query("SELECT * FROM dbUser WHERE id = :id")
    suspend fun getById(id: Int): DbUser

    @Transaction
    @Query("SELECT * FROM DbUser WHERE id = :id")
    suspend fun getByIdWithPosts(id: Int): UserWithPosts
}