package com.example.jsonplaceholderclient.di

import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository
import com.example.domain.GetPostUseCase
import com.example.domain.GetPostsForUserUseCase
import com.example.domain.GetPostsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
internal class DomainModule {
    @Provides
    fun getPostUseCase(
        @Named("local") localPost: PostRepository,
        @Named("remote") remotePost: PostRepository,
        @Named("local") localUser: UserRepository,
        @Named("remote") remoteUser: UserRepository

    ) =
        GetPostUseCase(localPost, remotePost, localUser, remoteUser)

    @Provides
    fun getPostsUseCase(
        @Named("local") localPost: PostRepository,
        @Named("remote") remotePost: PostRepository
    ) = GetPostsUseCase(localPost, remotePost)

    @Provides
    fun getPostsForUserUseCase(@Named("local") userRepository: UserRepository) = GetPostsForUserUseCase(userRepository)
}