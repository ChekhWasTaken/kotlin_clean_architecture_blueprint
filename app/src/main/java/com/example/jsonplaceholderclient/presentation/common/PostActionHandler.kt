package com.example.jsonplaceholderclient.presentation.common

import com.example.data.entity.Post

internal interface PostActionHandler {
    fun onPostAuthorClick(post: Post)
}