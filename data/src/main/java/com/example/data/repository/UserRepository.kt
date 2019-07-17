package com.example.data.repository

import com.example.data.Repository
import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.data.entity.UserQuery

interface UserRepository : Repository<User, UserQuery> {
    suspend fun getUserWithPosts(id: Int): List<Post>
}