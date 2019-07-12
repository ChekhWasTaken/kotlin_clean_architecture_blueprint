package com.example.domain

import com.example.data.entity.Post
import com.example.data.repository.Errors
import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository

class GetPostUseCase(
    private val localPost: PostRepository,
    private val remotePost: PostRepository,
    private val localUser: UserRepository,
    private val remoteUser: UserRepository
) :
    IOUseCase<Int, Post> {

    override suspend fun execute(request: Int): Post {
        return try {
            localPost.getPost(request)
        } catch (ex: Errors.NoSuchPostException) {
            val post = remotePost.getPost(request)
            localPost.addPost(post)

            localPost.getPost(request)
        } catch (ex: Errors.NoUserDataException) {
            val user = remoteUser.getUser(ex.userId)
            localUser.updateUser(user)

            localPost.getPost(request)
        }
    }
}