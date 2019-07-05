package com.example.db.dao

import androidx.room.*
import com.example.db.entity.DbPost
import com.example.db.entity.PostWithUser

@Dao
internal interface PostDao {
    @Insert
    suspend fun insert(vararg dbPosts: DbPost)

    @Update
    suspend fun update(vararg dbPosts: DbPost)

    @Delete
    suspend fun delete(vararg dbPosts: DbPost)

    @Transaction
    @Query("SELECT * FROM dbPost WHERE id = :id")
    suspend fun getById(id: Int): PostWithUser

    @Transaction
    @Query("SELECT * FROM dbPost")
    suspend fun get(): List<PostWithUser>
}