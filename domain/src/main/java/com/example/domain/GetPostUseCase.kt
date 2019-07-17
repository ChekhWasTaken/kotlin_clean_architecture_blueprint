package com.example.domain

import com.example.data.entity.Post
import com.example.data.entity.PostQuery
import com.example.data.entity.UserQuery
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
        val postQuery = PostQuery(id = request)

        return try {
            localPost.get(postQuery)
        } catch (ex: Errors.NoMatchForQuery) {
            val post = remotePost.get(postQuery)
            localPost.add(post)

            localPost.get(postQuery)
        } catch (ex: Errors.EmptyDataForQuery) {
            val user = remoteUser.get(UserQuery(id = localPost.getUserForPost(postQuery).id))
            localUser.update(user)

            localPost.get(postQuery)
        }
    }
}