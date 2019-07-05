package com.example.jsonplaceholderclient.di

import com.example.api.ApiServiceWrapper
import com.example.api.repository.RemotePostRepository
import com.example.api.repository.RemoteUserRepository
import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository
import com.example.db.AppDatabase
import com.example.db.repository.LocalPostRepository
import com.example.db.repository.LocalUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
internal object DataModule {
    @Provides
    @JvmStatic
    @Singleton
    @Named("local")
    fun localPostRepo(appDatabase: AppDatabase): PostRepository = LocalPostRepository(appDatabase)

    @Provides
    @JvmStatic
    @Singleton
    @Named("local")
    fun localUserRepo(appDatabase: AppDatabase): UserRepository = LocalUserRepository(appDatabase)


    @Provides
    @JvmStatic
    @Singleton
    @Named("remote")
    fun remotePostRepo(wrapper: ApiServiceWrapper): PostRepository = RemotePostRepository(wrapper)

    @Provides
    @JvmStatic
    @Singleton
    @Named("remote")
    fun remoteUserRepo(wrapper: ApiServiceWrapper): UserRepository = RemoteUserRepository(wrapper)

}