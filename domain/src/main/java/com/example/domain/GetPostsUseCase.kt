package com.example.domain

import com.example.data.entity.Post
import com.example.data.repository.PostRepository

class GetPostsUseCase constructor(private val localPost: PostRepository, private val remotePost: PostRepository) :
    IOUseCase<Unit, List<Post>> {
    override suspend fun execute(request: Unit): List<Post> {
        var posts = localPost.getAll()

        if (posts.isEmpty()) {
            val remotePosts = remotePost.getAll()

            localPost.add(*(remotePosts.toTypedArray()))
            posts = localPost.getAll()
        }

        return posts
    }
}