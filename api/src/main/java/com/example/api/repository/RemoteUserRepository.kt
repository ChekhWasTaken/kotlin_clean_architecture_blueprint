package com.example.api.repository

import com.example.api.ApiServiceWrapper
import com.example.api.dto.toUser
import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.data.entity.UserQuery
import com.example.data.repository.Errors
import com.example.data.repository.UserRepository

class RemoteUserRepository(private val serviceWrapper: ApiServiceWrapper) : UserRepository {
    override suspend fun getUserWithPosts(id: Int): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun get(query: UserQuery): User {
        return try {
            serviceWrapper.apiService.getUser(query.id).toUser()
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }
    }

    override suspend fun delete(item: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(vararg items: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun add(vararg items: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}