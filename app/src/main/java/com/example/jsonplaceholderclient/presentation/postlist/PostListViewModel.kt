package com.example.jsonplaceholderclient.presentation.postlist

import com.example.data.entity.Post
import com.example.domain.GetPostsUseCase
import com.example.framework.IOViewModel
import javax.inject.Inject

internal class PostListViewModel @Inject constructor(getPostsUseCase: GetPostsUseCase) :
    IOViewModel<Unit, List<Post>>(getPostsUseCase)