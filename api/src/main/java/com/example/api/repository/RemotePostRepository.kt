package com.example.api.repository

import com.example.api.ApiServiceWrapper
import com.example.api.dto.toPost
import com.example.data.entity.Post
import com.example.data.repository.Errors
import com.example.data.repository.PostRepository

class RemotePostRepository(private val serviceWrapper: ApiServiceWrapper) : PostRepository {
    override suspend fun addPost(vararg posts: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPost(id: Int): Post {
        try {
            val postDto = serviceWrapper.apiService.getPost(id)
            return postDto.toPost(serviceWrapper.apiService.getUser(postDto.userId!!))
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }

    }

    override suspend fun getPosts(): List<Post> {
        return try {
            serviceWrapper.apiService.getPosts().map { it.toPost() }
        } catch (ex: Throwable) {
            throw Errors.ContentNotAvailable(ex)
        }
    }
}