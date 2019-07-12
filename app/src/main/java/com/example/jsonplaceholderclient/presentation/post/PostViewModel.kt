package com.example.jsonplaceholderclient.presentation.post

import com.example.data.entity.Post
import com.example.domain.GetPostUseCase
import com.example.framework.IOViewModel
import javax.inject.Inject

internal class PostViewModel @Inject constructor(getPostUseCase: GetPostUseCase) :
    IOViewModel<Int, Post>(getPostUseCase)