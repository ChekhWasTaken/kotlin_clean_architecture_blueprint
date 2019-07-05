package com.example.db.repository

import com.example.data.entity.Post
import com.example.data.repository.Errors
import com.example.data.repository.PostRepository
import com.example.db.AppDatabase
import com.example.db.entity.*

class LocalPostRepository(private val appDatabase: AppDatabase) : PostRepository {
    override suspend fun addPost(vararg posts: Post) {
        val userList = mutableSetOf<DbUser>()
        val postList = posts.map {
            userList.add(DbUser.fromUser(it.user))
            DbPost.fromPost(it)
        }.toTypedArray()

        appDatabase.getUserDao().insert(*(userList.toTypedArray()))
        appDatabase.getPostDao().insert(*postList)
    }

    override suspend fun getPost(id: Int): Post {
        val post: Post

        try {
            post = appDatabase.getPostDao().getById(id).toPost()
        } catch (ex: IllegalArgumentException) {
            throw Errors.NoSuchPostException(id)
        }

        if (post.user.name.isEmpty()) throw Errors.NoUserDataException(id, post.user.id)

        return post
    }

    override suspend fun getPosts(): List<Post> = appDatabase.getPostDao().get().map { it.toPost() }
}