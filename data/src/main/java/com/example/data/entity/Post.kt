package com.example.data.entity

data class Post(
    val id: Int,
    val user: User,
    val title: String,
    val body: String
)