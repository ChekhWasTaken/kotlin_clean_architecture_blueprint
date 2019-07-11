package com.example.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.db.entity.DbPost
import com.example.db.entity.PostWithUser
import com.example.framework.BaseDao

@Dao
internal interface PostDao : BaseDao<DbPost> {
    @Transaction
    @Query("SELECT * FROM dbPost WHERE id = :id")
    suspend fun getById(id: Int): PostWithUser

    @Transaction
    @Query("SELECT * FROM dbPost")
    suspend fun get(): List<PostWithUser>
}