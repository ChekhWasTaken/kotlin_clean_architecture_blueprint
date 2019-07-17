package com.example.db.repository

import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.data.entity.UserQuery
import com.example.data.repository.Errors
import com.example.data.repository.UserRepository
import com.example.db.AppDatabase
import com.example.db.entity.DbUser
import com.example.db.entity.fromUser
import com.example.db.entity.toUser

class LocalUserRepository(private val appDatabase: AppDatabase) : UserRepository {
    override suspend fun get(query: UserQuery): User {
        return try {
            appDatabase.getUserDao().getById(query.id).toUser()
        } catch (ex: IllegalArgumentException) {
            throw Errors.NoMatchForQuery(query, ex)
        }
    }

    override suspend fun delete(item: User) {
        appDatabase.getUserDao().delete(DbUser.fromUser(item))
    }

    override suspend fun getAll(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(vararg items: User) {
        appDatabase.getUserDao().update(*(items.map { DbUser.fromUser(it) }.toTypedArray()))
    }

    override suspend fun add(vararg items: User) {
        appDatabase.getUserDao().insert(*(items.map { DbUser.fromUser(it) }.toTypedArray()))
    }


    override suspend fun getUserWithPosts(id: Int): List<Post> {
        val userWithPosts = appDatabase.getUserDao().getByIdWithPosts(id)

        return userWithPosts.dbUserPosts.map { Post(it.id, userWithPosts.dbUser.toUser(), it.title, it.body) }
    }
}