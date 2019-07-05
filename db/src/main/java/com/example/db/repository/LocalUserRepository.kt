package com.example.db.repository

import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.data.repository.Errors
import com.example.data.repository.UserRepository
import com.example.db.AppDatabase
import com.example.db.entity.DbUser
import com.example.db.entity.fromUser
import com.example.db.entity.toUser

class LocalUserRepository(private val appDatabase: AppDatabase) : UserRepository {
    override suspend fun getUserWithPosts(id: Int): List<Post> {
        val userWithPosts = appDatabase.getUserDao().getByIdWithPosts(id)

        return userWithPosts.dbUserPosts.map { Post(it.id, userWithPosts.dbUser.toUser(), it.title, it.body) }
    }

    override suspend fun updateUser(vararg users: User) {
        appDatabase.getUserDao().update(*(users.map { DbUser.fromUser(it) }.toTypedArray()))
    }

    override suspend fun addUser(vararg users: User) {
        appDatabase.getUserDao().insert(*(users.map { DbUser.fromUser(it) }.toTypedArray()))
    }

    override suspend fun getUser(id: Int): User {
        return try {
            appDatabase.getUserDao().getById(id).toUser()
        } catch (ex: IllegalArgumentException) {
            throw Errors.NoSuchUserException(id)
        }
    }
}