package com.example.data.entity

import com.example.data.QueryObject

data class Post(
    val id: Int,
    val user: User,
    val title: String,
    val body: String
)

data class PostQuery(val id: Int = -1, val user: User? = null, val title: String = "", val body: String = "") :
    QueryObject