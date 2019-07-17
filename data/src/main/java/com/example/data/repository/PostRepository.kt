package com.example.data.repository

import com.example.data.Repository
import com.example.data.entity.Post
import com.example.data.entity.PostQuery
import com.example.data.entity.User

interface PostRepository : Repository<Post, PostQuery> {
    suspend fun getUserForPost(postQuery: PostQuery): User
}