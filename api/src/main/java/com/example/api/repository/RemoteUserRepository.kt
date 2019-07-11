package com.example.api.repository

import com.example.api.ApiServiceWrapper
import com.example.api.dto.toUser
import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.data.repository.Errors
import com.example.data.repository.UserRepository

class RemoteUserRepository(private val serviceWrapper: ApiServiceWrapper) : UserRepository {
    override suspend fun getUserWithPosts(id: Int): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateUser(vararg users: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addUser(vararg users: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUser(id: Int): User {
        return try {
            serviceWrapper.apiService.getUser(id).toUser()
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }
    }
}