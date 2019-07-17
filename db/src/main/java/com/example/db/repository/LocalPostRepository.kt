package com.example.db.repository

import com.example.data.entity.Post
import com.example.data.entity.PostQuery
import com.example.data.repository.Errors
import com.example.data.repository.PostRepository
import com.example.db.AppDatabase
import com.example.db.entity.*

class LocalPostRepository(private val appDatabase: AppDatabase) : PostRepository {
    override suspend fun getUserForPost(postQuery: PostQuery) =
        appDatabase.getPostDao().getById(postQuery.id).dbUser[0].toUser()

    override suspend fun get(query: PostQuery): Post {
        val post: Post

        try {
            post = appDatabase.getPostDao().getById(query.id).toPost()
        } catch (ex: IllegalArgumentException) {
            throw Errors.NoMatchForQuery(query, ex)
        }

        if (post.user.name.isEmpty()) throw Errors.EmptyDataForQuery(query, NoUserDataException(query.id, post.user.id))

        return post
    }

    override suspend fun delete(item: Post) = appDatabase.getPostDao().delete(DbPost.fromPost(item))

    override suspend fun getAll(): List<Post> = appDatabase.getPostDao().get().map { it.toPost() }

    override suspend fun update(vararg items: Post) {
        val userList = mutableSetOf<DbUser>()
        val postList = items.map {
            userList.add(DbUser.fromUser(it.user))
            DbPost.fromPost(it)
        }.toTypedArray()

        appDatabase.getUserDao().update(*(userList.toTypedArray()))
        appDatabase.getPostDao().update(*postList)
    }

    override suspend fun add(vararg items: Post) {
        val userList = mutableSetOf<DbUser>()
        val postList = items.map {
            userList.add(DbUser.fromUser(it.user))
            DbPost.fromPost(it)
        }.toTypedArray()

        appDatabase.getUserDao().insert(*(userList.toTypedArray()))
        appDatabase.getPostDao().insert(*postList)
    }
}

class NoUserDataException(postId: Int, userId: Int) :
    RuntimeException("No user data found for userId=$userId, postId=$postId")