package com.example.jsonplaceholderclient.presentation.user

import com.example.data.entity.Post
import com.example.domain.GetPostsForUserUseCase
import com.example.framework.IOViewModel
import javax.inject.Inject

internal class UserViewModel @Inject constructor(getPostsForUserUseCase: GetPostsForUserUseCase) :
    IOViewModel<Int, List<Post>>(getPostsForUserUseCase)