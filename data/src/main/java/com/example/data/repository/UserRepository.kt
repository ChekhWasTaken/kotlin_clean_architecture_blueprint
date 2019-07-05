package com.example.data.repository

import com.example.data.entity.Post
import com.example.data.entity.User

interface UserRepository {
    suspend fun getUser(id: Int): User

    suspend fun addUser(vararg users: User)

    suspend fun updateUser(vararg users: User)

    suspend fun getUserWithPosts(id: Int): List<Post>
}