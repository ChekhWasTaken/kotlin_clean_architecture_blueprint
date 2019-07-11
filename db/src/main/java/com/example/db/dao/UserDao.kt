package com.example.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.db.entity.DbUser
import com.example.db.entity.UserWithPosts
import com.example.framework.BaseDao

@Dao
internal interface UserDao : BaseDao<DbUser> {
    @Query("SELECT * FROM dbUser WHERE id = :id")
    suspend fun getById(id: Int): DbUser

    @Transaction
    @Query("SELECT * FROM dbUser WHERE id = :id")
    suspend fun getByIdWithPosts(id: Int): UserWithPosts
}