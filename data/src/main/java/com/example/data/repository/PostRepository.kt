package com.example.data.repository

import com.example.data.entity.Post

interface PostRepository {
    @Throws(Errors.NoSuchPostException::class, Errors.NoUserDataException::class)
    suspend fun getPost(id: Int): Post

    suspend fun getPosts(): List<Post>

    suspend fun addPost(vararg posts: Post)
}