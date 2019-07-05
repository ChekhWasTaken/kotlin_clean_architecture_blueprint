package com.example.api

import com.example.api.dto.PostDto
import com.example.api.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

class ApiServiceWrapper {
    internal lateinit var apiService: ApiService
}

internal interface ApiService {
    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostDto

    @GET("/posts")
    suspend fun getPosts(): List<PostDto>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserDto
}