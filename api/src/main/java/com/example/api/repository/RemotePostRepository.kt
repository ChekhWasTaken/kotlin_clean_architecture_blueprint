package com.example.api.repository

import com.example.api.ApiServiceWrapper
import com.example.api.dto.toPost
import com.example.data.entity.Post
import com.example.data.entity.PostQuery
import com.example.data.repository.Errors
import com.example.data.repository.PostRepository

class RemotePostRepository(private val serviceWrapper: ApiServiceWrapper) : PostRepository {
    override suspend fun getUserForPost(postQuery: PostQuery) = get(postQuery).user

    override suspend fun get(query: PostQuery): Post {
        return try {
            with(serviceWrapper.apiService.getPost(query.id)) {
                toPost(serviceWrapper.apiService.getUser(userId!!))
            }
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }
    }

    override suspend fun delete(item: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): List<Post> {
        return try {
            serviceWrapper.apiService.getPosts().map { it.toPost() }
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }
    }

    override suspend fun update(vararg items: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun add(vararg items: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}