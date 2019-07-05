package com.example.domain

import com.example.data.entity.Post
import com.example.data.repository.UserRepository

class GetPostsForUserUseCase(private val userRepository: UserRepository) : UseCase<Int, List<Post>> {
    override suspend fun execute(request: Int): List<Post> = userRepository.getUserWithPosts(request)
}